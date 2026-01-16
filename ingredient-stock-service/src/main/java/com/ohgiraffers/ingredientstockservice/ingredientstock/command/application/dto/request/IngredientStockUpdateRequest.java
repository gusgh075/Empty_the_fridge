package com.ohgiraffers.ingredientstockservice.ingredientstock.command.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Schema(description = "식재료 재고 수량 수정 요청")
@Getter
@RequiredArgsConstructor
public class IngredientStockUpdateRequest {

    @Schema(description = "식재료 재고 번호", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "식재료 번호는 필수 입력값 입니다")
    private final long ingredientStockNo;

    @Schema(description = "사용한 식재료 수량", example = "100", minimum = "0")
    @PositiveOrZero(message = "사용한 식재료 양은 0이상이어야 합니다")
    private final long ingredientStockUsedQuantity;

}
