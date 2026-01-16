package com.ohgiraffers.ingredientstockservice.statistics.query.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CategoryPurchaseDTO {

	private IngredientCategory category;
	private Long totalCost;

	public String getCategoryName() {
		return category != null ? category.getDescription() : "기타";
	}
}