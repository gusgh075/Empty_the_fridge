package com.ohgiraffers.mainservice.ingredientstock.command.application.dto.response;

import com.ohgiraffers.mainservice.ingredientstock.command.domain.aggregate.StockUnit;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class IngredientStockUpdateResponse {

    private final String ingredientStockName;
    private final long ingredientStockTotalQuantity;
    private final long ingredientStockNowQuantity;
    private final StockUnit ingredientStockUnit;

}
