package com.ohgiraffers.mainservice.ingredientstock.query.dto.response;

import com.ohgiraffers.mainservice.ingredientstock.command.domain.aggregate.IngredientStock;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class IngredientStockResponse {

    private final List<IngredientStock> ingredientStocks;

}
