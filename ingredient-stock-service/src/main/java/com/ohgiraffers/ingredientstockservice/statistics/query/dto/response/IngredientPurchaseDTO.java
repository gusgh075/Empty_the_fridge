package com.ohgiraffers.ingredientstockservice.statistics.query.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IngredientPurchaseDTO {
    private String name;        // 식자재 이름
    private long totalCost;     // 해당 식자재 총 구매 금액 (금액이 커질 수 있으니 long 추천)
    private double percentage;  // 비중 (예: 35.5%)
}