package com.ohgiraffers.recipeservice.recipe.command.application.dto.response;

import com.ohgiraffers.recipeservice.recipe.command.application.dto.DishCategoryEnum;
import com.ohgiraffers.recipeservice.recipe.command.application.dto.RecipeIngredient;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "레시피 추천 결과 응답 DTO")
public class RecommendRecipeResponse {
  @Schema(description = "요리 카테고리", implementation = DishCategoryEnum.class)
  private DishCategoryEnum dishCategoryEnum; // 요리 카테고리
  @Schema(description = "추천 요리 이름", example = "제육볶음")
  private String dishName; // 추천 요리 이름
  @Schema(description = "필요한 재료 목록", implementation = RecipeIngredient.class)
  private List<RecipeIngredient> ingredients; // 필요한 재료 정보
  @Schema(description = "대체 가능 재료 목록", implementation = Substitution.class)
  private List<Substitution> substitutions; // 대체 가능 재료
  @Schema(description = "조리법 (단계별)", example = "[\"고기를 볶는다\", \"양념을 넣는다\"]")
  private List<String> cookery; // 조리법 (단계별)
  @Schema(description = "조리 팁", example = "[\"센 불에 볶아야 맛있습니다\"]")
  private List<String> tips; // 조리 팁

  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  @Schema(description = "대체 재료 정보")
  public static class Substitution {
    @Schema(description = "원래 재료", example = "설탕")
    private String original; // 원래 재료
    @Schema(description = "대체 재료", example = "올리고당")
    private String replacement; // 대체 재료
  }
}