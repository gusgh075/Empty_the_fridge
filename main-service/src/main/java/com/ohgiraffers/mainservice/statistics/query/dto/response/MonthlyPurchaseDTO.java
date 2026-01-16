package com.ohgiraffers.recipeservice.statistics.query.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MonthlyPurchaseDTO {
	private String name; // 식자재 이름
	private int cost;             // 구매 가격
	private String quantity;          // 구매 수량
	private String purchaseDate;   // 구매 일자
	private String category;
}