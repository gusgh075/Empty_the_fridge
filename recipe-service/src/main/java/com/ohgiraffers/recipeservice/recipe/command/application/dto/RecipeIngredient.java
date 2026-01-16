package com.ohgiraffers.recipeservice.recipe.command.application.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 레시피에 들어갈 개별 Ingredient를 담는 클래스
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "레시피 재료 정보")
public class RecipeIngredient {
	@Schema(description = "재료 이름", example = "당근")
	private String name;
	@Schema(description = "재료 수량", example = "1.5")
	private double amount;
	@Schema(description = "단위", example = "개")
	private String unit;

	@Override
	public String toString() {
		return "\"%s\": \"%.1f%s\"".formatted(name, amount, unit);
	}

	/**
	 * 리스트를 문자열로 변환하는 메서드
	 * 결과 예: {"당근": "1.000000개", "양파": "0.500000개"}
	 */
	public static String listToString(List<RecipeIngredient> ingredients) {
		if (ingredients == null || ingredients.isEmpty()) {
			return "{}";
		}

		return ingredients.stream()
				.map(RecipeIngredient::toString) // 1. 각 객체의 toString() 호출
				.collect(Collectors.joining(", ", "{", "}")); // 2. 구분자(", "), 접두사("{"), 접미사("}") 적용
	}

	/**
	 * 문자열을 다시 리스트로 변환
	 * 
	 * @param ingredientsString 변환할 문자열 (예: {"당근": "1.000000개", "양파": "0.500000개"})
	 * @return RecipeIngredientDTO 리스트
	 */
	public static List<RecipeIngredient> stringToList(String ingredientsString) {
		List<RecipeIngredient> ingredients = new ArrayList<>();

		if (ingredientsString == null || ingredientsString.isBlank() || "{}".equals(ingredientsString)) {
			return ingredients;
		}

		// 정규표현식 설명:
		// \"([^\"]+)\" : 큰따옴표 안의 재료명을 캡처 (그룹 1)
		// :\\s* : 콜론과 뒤의 공백 허용
		// \"([\\d.]+)([^\"]*)\" : 큰따옴표 안에서 숫자(그룹 2)와 그 뒤의 단위 문자열(그룹 3)을 분리하여 캡처
		Pattern pattern = Pattern.compile("\"([^\"]+)\":\\s*\"([\\d.]+)([^\"]*)\"");
		Matcher matcher = pattern.matcher(ingredientsString);

		while (matcher.find()) {
			RecipeIngredient dto = new RecipeIngredient();
			dto.name = matcher.group(1); // 재료명
			dto.amount = Double.parseDouble(matcher.group(2)); // 수량 (문자열 -> double)
			dto.unit = matcher.group(3); // 단위

			ingredients.add(dto);
		}

		return ingredients;
	}
}
