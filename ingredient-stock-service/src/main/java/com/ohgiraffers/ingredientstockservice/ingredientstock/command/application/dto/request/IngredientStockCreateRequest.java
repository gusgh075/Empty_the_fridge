package com.ohgiraffers.ingredientstockservice.ingredientstock.command.application.dto.request;


import com.ohgiraffers.ingredientstockservice.ingredientstock.command.domain.aggregate.StockCategory;
import com.ohgiraffers.ingredientstockservice.ingredientstock.command.domain.aggregate.StockUnit;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class IngredientStockCreateRequest {

    @NotNull(message = "식재료 이름은 필수 입력 값입니다")
    private final String ingredientStockName;

    @NotNull(message = "유통기한은 필수 입력 값입니다")
    @FutureOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private final LocalDate ingredientStockExpiredAt;

    @NotNull(message = "식재료량 단위는 필수 입력 값입니다")
    private final StockUnit ingredientStockUnit;

    @NotNull(message = "식재료량은 필수 입력 값입니다")
    @Min(1)
    private final long ingredientStockTotalQuantity;

    @NotNull(message = "식재료값은 필수 입력 값입니다")
    @Min(1)
    private final long ingredientStockTotalCost;

    @NotNull(message = "식재료 종류는 필수 입력 값입니다")
    private final StockCategory ingredientStockCategory;

    @NotNull(message = "식재료 구매일은 필수 입력 값입니다")
    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private final LocalDate ingredientStockBoughtAt;

}
