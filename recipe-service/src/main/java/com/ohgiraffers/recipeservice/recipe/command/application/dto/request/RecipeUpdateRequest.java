package com.ohgiraffers.recipeservice.recipe.command.application.dto.request;

import com.ohgiraffers.recipeservice.recipe.command.application.dto.RecipeIngredient;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import java.util.List;


@Getter
@Setter
@Schema(description = "레시피 수정 요청 DTO")
public class RecipeUpdateRequest {

    @Schema(description = "레시피 번호", example = "1")
    private Integer recipeNo;
    @Schema(description = "재료 목록")
    private List<RecipeIngredient> ingredients;
    @Schema(description = "조리법 단계 목록", example = "[\"물을 끓인다\", \"김치를 넣는다\"]")
    private List<String> cookery;

}