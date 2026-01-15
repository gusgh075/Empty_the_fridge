package com.ohgiraffers.ingredientstockservice.ingredientstock.command.application.dto.response;


import com.ohgiraffers.ingredientstockservice.ingredientstock.command.domain.aggregate.IngredientStock;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class IngredientStockCreateResponse {

    private final IngredientStock ingredientStock;

}
