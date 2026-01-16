package com.ohgiraffers.ingredientstockservice.ingredientstock.command.application.dto.request;


import com.ohgiraffers.ingredientstockservice.ingredientstock.command.domain.aggregate.StockCategory;
import com.ohgiraffers.ingredientstockservice.ingredientstock.command.domain.aggregate.StockUnit;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Schema(description = "식재료 재고 등록 요청")
@Getter
@RequiredArgsConstructor
public class IngredientStockCreateRequest {

    @Schema(description = "식재료 이름", example = "닭가슴살", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "식재료 이름은 필수 입력 값입니다")
    private final String ingredientStockName;

    @Schema(description = "유통기한", example = "2026-02-15", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "유통기한은 필수 입력 값입니다")
    @FutureOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private final LocalDate ingredientStockExpiredAt;

    @Schema(description = "수량 단위", example = "G", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "식재료량 단위는 필수 입력 값입니다")
    private final StockUnit ingredientStockUnit;

    @Schema(description = "총 수량", example = "500", minimum = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "식재료량은 필수 입력 값입니다")
    @Min(1)
    private final long ingredientStockTotalQuantity;

    @Schema(description = "총 금액 (원)", example = "12000", minimum = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "식재료값은 필수 입력 값입니다")
    @Min(1)
    private final long ingredientStockTotalCost;

    @Schema(description = "식재료 카테고리", example = "MEAT", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "식재료 종류는 필수 입력 값입니다")
    private final StockCategory ingredientStockCategory;

    @Schema(description = "구매일", example = "2026-01-10", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "식재료 구매일은 필수 입력 값입니다")
    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private final LocalDate ingredientStockBoughtAt;

}
