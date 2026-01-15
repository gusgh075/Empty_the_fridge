package com.ohgiraffers.ingredientstockservice.ingredientstock.command.application.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class DisposalCreateRequest {

    @NotNull
    long ingredientStockNo; // 식재료 번호

    @NotNull
    long disposalQuantity; // 폐기 수량

    @NotNull
    @Max(100)
    String disposalReason; // 폐기 이력

}
