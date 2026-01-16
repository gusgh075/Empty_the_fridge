package com.ohgiraffers.recipeservice.recipe.query.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(description = "추천 레시피 정보 응답 DTO")
public class RecommendRecipeDTO {
    @Schema(description = "추천 레시피 번호", example = "100")
    private Integer id;
    @Schema(description = "사용자 번호", example = "5")
    private Integer userNo;
    @Schema(description = "카테고리 번호", example = "2")
    private Integer dishCategoryNo;
    @Schema(description = "추천 요리 이름", example = "마라탕")
    private String rcdRecipeDishName;
    @Schema(description = "추천 레시피 재료 (JSON 문자열)")
    private String rcdRecipeIngredients;
    @Schema(description = "추천 레시피 대체 재료 (JSON 문자열)")
    private String rcdRecipeSubstitutions;
    @Schema(description = "추천 레시피 조리법 (문자열)")
    private String rcdRecipeCookery;
    @Schema(description = "조리 팁 (JSON 문자열)")
    private String rcdRecipeTips;
}
