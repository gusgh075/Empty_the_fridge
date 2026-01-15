package com.ohgiraffers.ingredientstockservice.ingredientstock.query.dto.response;

import com.ohgiraffers.ingredientstockservice.ingredientstock.command.domain.aggregate.IngredientStock;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class IngredientStockResponse {

    private final List<IngredientStock> ingredientStocks;

}
