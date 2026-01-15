package com.ohgiraffers.ingredientstockservice.ingredientstock.command.application.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class IngredientStockUpdateRequest {

    @NotNull(message = "식재료 번호는 필수 입력값 입니다")
    private final long ingredientStockNo;

    @PositiveOrZero(message = "사용한 식재료 양은 0이상이어야 합니다")
    private final long ingredientStockUsedQuantity;

}
