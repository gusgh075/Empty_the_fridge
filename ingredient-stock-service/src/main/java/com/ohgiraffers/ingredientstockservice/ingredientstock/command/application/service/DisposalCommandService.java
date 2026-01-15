package com.ohgiraffers.ingredientstockservice.ingredientstock.command.application.service;

import com.ohgiraffers.ingredientstockservice.exception.BusinessException;
import com.ohgiraffers.ingredientstockservice.exception.ErrorCode;
import com.ohgiraffers.ingredientstockservice.ingredientstock.command.application.dto.request.DisposalCreateRequest;
import com.ohgiraffers.ingredientstockservice.ingredientstock.command.application.dto.response.DisposalCreateResponse;
import com.ohgiraffers.ingredientstockservice.ingredientstock.command.domain.aggregate.DisposalHistory;
import com.ohgiraffers.ingredientstockservice.ingredientstock.command.domain.aggregate.IngredientStock;
import com.ohgiraffers.ingredientstockservice.ingredientstock.command.domain.repository.DisposalDomainRepository;
import com.ohgiraffers.ingredientstockservice.ingredientstock.command.domain.repository.IngredientStockDomainRepository;
import com.ohgiraffers.ingredientstockservice.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DisposalCommandService {

    private final DisposalDomainRepository disposalDomainRepository; // JPA Repository
    private final IngredientStockDomainRepository ingredientStockDomainRepository;

    @Transactional
    public DisposalCreateResponse createDisposal(String providedUserNo, DisposalCreateRequest disposalCreateRequest) {
        // createDispsal process
        long userNo = Long.parseLong(providedUserNo);

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
                ingredientStock.getIngredientStockNo(),
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
