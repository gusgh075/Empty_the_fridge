package com.ohgiraffers.mainservice.ingredientstock.command.application.service;

import com.ohgiraffers.mainservice.exception.BusinessException;
import com.ohgiraffers.mainservice.exception.ErrorCode;
import com.ohgiraffers.mainservice.ingredientstock.command.application.dto.request.DisposalCreateRequest;
import com.ohgiraffers.mainservice.ingredientstock.command.application.dto.response.DisposalCreateResponse;
import com.ohgiraffers.mainservice.ingredientstock.command.domain.aggregate.DisposalHistory;
import com.ohgiraffers.mainservice.ingredientstock.command.domain.aggregate.IngredientStock;
import com.ohgiraffers.mainservice.ingredientstock.command.domain.repository.DisposalDomainRepository;
import com.ohgiraffers.mainservice.ingredientstock.command.domain.repository.IngredientStockDomainRepository;
import com.ohgiraffers.mainservice.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DisposalCommandService {

    private final JwtTokenProvider jwtTokenProvider;
    private final DisposalDomainRepository disposalDomainRepository; // JPA Repository
    private final IngredientStockDomainRepository ingredientStockDomainRepository;

    @Transactional
    public DisposalCreateResponse createDisposal(String refreshToken, DisposalCreateRequest disposalCreateRequest) {
        // createDispsal process
        // 1) get userNo from refreshToken
        this.jwtTokenProvider.validateToken(refreshToken);
        long userNo = Long.parseLong(this.jwtTokenProvider.getUserNoFromJWT(refreshToken));

        // 2) get ingredientStockNo from disposalCreateRequest
        long ingredientStockNo = disposalCreateRequest.getIngredientStockNo();

        // 3) get disposalQuantity from disposalCreateRequest
        long disposalQuantity = disposalCreateRequest.getDisposalQuantity();

        // 4) get disposalReason from disposalCreateRequest
        String disposalReason = disposalCreateRequest.getDisposalReason();

        // 5) get ingredient_stock by ingredientStockNo and calculate disposal_cost
        // Double disposal_cost= disposalQuantity * (ingredient_stock_total_cost/ingredient_stock_total_quantity)
        IngredientStock ingredientStock = this.ingredientStockDomainRepository
                .findByUserNoAndIngredientStockNo(userNo, ingredientStockNo)
                .orElseThrow(() -> new BusinessException(ErrorCode.INGREDIENT_STOCK_NOT_FOUND));

        // Calculate disposal cost
        double costPerUnit = (double) ingredientStock.getIngredientStockTotalCost() / ingredientStock.getIngredientStockTotalQuantity();
        double disposalCost = disposalQuantity * costPerUnit;

        // 6) set ingredient_stock_now_quantity -= disposalQuantity
        long currentQuantity = ingredientStock.getIngredientStockNowQuantity();
        if (currentQuantity < disposalQuantity) {
            throw new BusinessException(ErrorCode.INSUFFICIENT_STOCK);
        }
        ingredientStock.updateIngredientStockNowQuantity(disposalQuantity);

        // 7) create disposalHistory Entity
        DisposalHistory disposalHistory = DisposalHistory.createDisposalHistory(
                ingredientStock,
                userNo,
                disposalQuantity,
                disposalCost,
                disposalReason
        );

        // 8) save disposal Entity to database disposal_histories table
        DisposalHistory result = this.disposalDomainRepository.save(disposalHistory);
        return DisposalCreateResponse.builder().disposalHistory(result).build();
    }


}
