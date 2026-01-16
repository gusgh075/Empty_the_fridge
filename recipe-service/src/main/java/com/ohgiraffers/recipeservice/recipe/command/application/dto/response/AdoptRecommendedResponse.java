package com.ohgiraffers.recipeservice.recipe.command.application.dto.response;

import com.ohgiraffers.recipeservice.recipe.query.dto.response.DishDTO;
import com.ohgiraffers.recipeservice.recipe.query.dto.response.RecipeDTO;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "추천 레시피 채택 응답 DTO")
public class AdoptRecommendedResponse {
	@Schema(description = "생성된 레시피 정보", implementation = RecipeDTO.class)
	private RecipeDTO recipe;
	@Schema(description = "생성된 요리 정보", implementation = DishDTO.class)
	private DishDTO dish;
}
