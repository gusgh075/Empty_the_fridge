package com.ohgiraffers.recipeservice.recipe.command.application.dto.request;

import com.ohgiraffers.recipeservice.recipe.command.application.dto.DishCategoryEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.Set;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "레시피 추천 요청 DTO")
public class RecipeRecommendRequest {
  @Schema(description = "추천받을 요리 이름", example = "파스타")
  private String dishName; // 추천받을 요리 이름
  @Schema(description = "사용할 재료 목록", example = "[\"양파\", \"마늘\"]")
  private List<String> ingredients; // 사용할 재료
  @Schema(description = "못 먹는 재료 목록", example = "[\"오이\"]")
  private List<String> dislikedIngredients; // 못 먹는 재료
  @Schema(description = "요리 숙련도", example = "BEGINNER")
  private CookingLevel skillLevel; // 요리 숙련도
  @Schema(description = "보유 조리 도구 목록", example = "[\"프라이팬\", \"전자레인지\"]")
  private List<String> tools; // 보유 조리 도구
  @Schema(description = "원하는 음식 유형 (최대 3개)", example = "[\"KOREAN\", \"WESTERN\"]")
  private Set<DishCategoryEnum> foodTypes; // 원하는 음식 유형 (최대 3개)
  @Schema(description = "취향", example = "매운맛")
  private String preference; // 취향
  @Schema(description = "몇 인분", example = "2")
  private int servings; // 몇 인분

  public enum CookingLevel {
    BEGINNER, INTERMEDIATE, ADVANCED
  }
}