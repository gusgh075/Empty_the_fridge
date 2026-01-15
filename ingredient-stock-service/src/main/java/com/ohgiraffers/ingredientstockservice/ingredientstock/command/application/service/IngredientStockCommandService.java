package com.ohgiraffers.ingredientstockservice.ingredientstock.command.application.service;


import com.ohgiraffers.ingredientstockservice.common.client.NotificationServiceClient;
import com.ohgiraffers.ingredientstockservice.common.dto.NotificationCreateRequest;
import com.ohgiraffers.ingredientstockservice.config.Constants;
import com.ohgiraffers.ingredientstockservice.exception.BusinessException;
import com.ohgiraffers.ingredientstockservice.exception.ErrorCode;
import com.ohgiraffers.ingredientstockservice.ingredientstock.command.application.dto.request.IngredientStockCreateRequest;
import com.ohgiraffers.ingredientstockservice.ingredientstock.command.application.dto.request.IngredientStockUpdateRequest;
import com.ohgiraffers.ingredientstockservice.ingredientstock.command.application.dto.response.IngredientStockCreateResponse;
import com.ohgiraffers.ingredientstockservice.ingredientstock.command.application.dto.response.IngredientStockUpdateResponse;
import com.ohgiraffers.ingredientstockservice.ingredientstock.command.domain.aggregate.IngredientStock;
import com.ohgiraffers.ingredientstockservice.ingredientstock.command.domain.repository.IngredientStockDomainRepository;
import com.ohgiraffers.ingredientstockservice.ingredientstock.command.domain.service.IngredientStockDomainService;
import com.ohgiraffers.ingredientstockservice.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IngredientStockCommandService {

    private final JwtTokenProvider jwtTokenProvider;
    private final IngredientStockDomainService ingredientStockDomainService;
    private final ModelMapper modelMapper;
    private final IngredientStockDomainRepository ingredientStockDomainRepository;
    private final NotificationServiceClient notificationServiceClient;

    @Transactional
    public IngredientStockCreateResponse registIngredientStock(
            String providedUserNo,
            IngredientStockCreateRequest ingredientStockCreateRequest
    ) {
        // userNo parse to long
        long userNo = Long.parseLong(providedUserNo);

        // 2. 사용자로부터 입력받은 ingredientStockCreateRequest 검증 (필수?)
        this.ingredientStockDomainService.validateValue(ingredientStockCreateRequest);

        // 3. ingredientStockCreateRequest(DTO) -> ingredientStock Entity로 변환
        IngredientStock ingredientStock = this.modelMapper.map(ingredientStockCreateRequest, IngredientStock.class);
        ingredientStock.InitTime();
        ingredientStock.setUserNo(userNo);

        // 4. ingredient_stock DB table 에 저장
        this.ingredientStockDomainRepository.save(ingredientStock);

        return new IngredientStockCreateResponse(ingredientStock);
    }

    @Transactional
    public IngredientStockUpdateResponse updateIngredientStock(
            String providedUserNo, // user_no를 가져오기 위한 Token 객체
            IngredientStockUpdateRequest ingredientStockUpdateRequest // 업데이트 내용을 담고있는 객체
    ) {
        // 2. user_no, ingredient_stock_no 가져오기
        long userNo = Long.parseLong(providedUserNo);
        long ingredientStockNo = ingredientStockUpdateRequest.getIngredientStockNo();

        // 3. ingredient_stock table에서 user_no, ingredient_stock_no 가 일치하는 칼럼
        // ingredientStockDomainRepository사용해 가져오기
        IngredientStock ingredientStock = this.ingredientStockDomainRepository
                .findByUserNoAndIngredientStockNo(userNo, ingredientStockNo)
                .orElseThrow(() -> new BusinessException(ErrorCode.INGREDIENT_STOCK_NOT_FOUND));

        // 4. ingredientStockUpdateRequest.getIngredientStockNowQuantity() 가져와 불러온 칼럼에 업데이트
        ingredientStock.updateIngredientStockNowQuantity(ingredientStockUpdateRequest.getIngredientStockUsedQuantity());

        // 4-1. if ingredientStock.getIngredientStockNowQuantity() is under 0, print an error message and throw an error
        if (ingredientStock.getIngredientStockNowQuantity() < 0) {
            throw new BusinessException(ErrorCode.INSUFFICIENT_STOCK);
        }

        // 5. update한 IngredientStock object return
        return IngredientStockUpdateResponse.builder()
                .ingredientStockName(ingredientStock.getIngredientStockName())
                .ingredientStockTotalQuantity(ingredientStock.getIngredientStockTotalQuantity())
                .ingredientStockNowQuantity(ingredientStock.getIngredientStockNowQuantity())
                .ingredientStockUnit(ingredientStock.getIngredientStockUnit())
                .build();
    }

    /* 식자재를 필터링하여 Notification DB에 저장하는 메서드 */
    @Transactional
    public void setIngredientStockNotice(String providedUserNo) {

        // user_no 가져오기
        long userNo = Long.parseLong(providedUserNo);

        // user_no가 일치하는 ingredient_stock 데이터 모두 가져오기
        List<IngredientStock> ingredientStocks = this.ingredientStockDomainRepository.findAllByUserNo(userNo);

        // 유통기한임박 ingredient stock 추출
        List<IngredientStock> filteredStockListA = this.ingredientStockDomainService.filterExpiredSoonStock(ingredientStocks);

        // 식재료소진임박 ingredient stock 추출
        List<IngredientStock> filteredStockListB = this.ingredientStockDomainService.filterLowStock(ingredientStocks);

        // filteredStockListA, filteredStockListB를 notification 생성 요청 DTO로 변환
        List<NotificationCreateRequest> notificationRequests = new ArrayList<>();

        // case A) notificationContent= ingredientStockName + "유통기한이" + ingredientStockExpiredAt - now() + "일 남음"
        LocalDate now = LocalDate.now();
        for (IngredientStock stock : filteredStockListA) {
            long daysRemaining = ChronoUnit.DAYS.between(now, stock.getIngredientStockExpiredAt());
            String content = stock.getIngredientStockName() + " 유통기한이 " + daysRemaining + "일 남음";
            NotificationCreateRequest request = NotificationCreateRequest.builder()
                    .userNo(userNo)
                    .notificationTypeNo(Constants.NOTICE_TYPE_EXPIRED_SOON)
                    .notificationContent(content)
                    .build();
            notificationRequests.add(request);
        }

        // case B) notificationContent= ingredientStockName + "재고가" + ingredientStockNowQuantity + stockUnit + " 남음"
        for (IngredientStock stock : filteredStockListB) {
            String content = stock.getIngredientStockName() + " 재고가 "
                    + stock.getIngredientStockNowQuantity()
                    + stock.getIngredientStockUnit() + " 남음";
            NotificationCreateRequest request = NotificationCreateRequest.builder()
                    .userNo(userNo)
                    .notificationTypeNo(Constants.NOTICE_TYPE_LOW_STOCK)
                    .notificationContent(content)
                    .build();
            notificationRequests.add(request);
        }

        // Feign Client를 통해 main-service로 알림 생성 요청
        if (!notificationRequests.isEmpty()) {
            this.notificationServiceClient.createNotifications(notificationRequests);
        }

    }
}
