package com.ohgiraffers.mainservice.ingredientstock.command.application.dto.response;


import com.ohgiraffers.mainservice.ingredientstock.command.domain.aggregate.IngredientStock;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class IngredientStockCreateResponse {

    private final IngredientStock ingredientStock;

}
