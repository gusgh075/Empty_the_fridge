package com.ohgiraffers.ingredientstockservice.ingredientstock.command.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Schema(description = "식재료 폐기 기록 요청")
@Getter
public class DisposalCreateRequest {

    @Schema(description = "폐기할 식재료 재고 번호", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    long ingredientStockNo;

    @Schema(description = "폐기 수량", example = "200", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    long disposalQuantity;

    @Schema(description = "폐기 사유", example = "유통기한 만료", maxLength = 100, requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    @Max(100)
    String disposalReason;

}
