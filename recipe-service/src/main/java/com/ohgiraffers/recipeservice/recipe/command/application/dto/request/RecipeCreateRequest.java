package com.ohgiraffers.recipeservice.recipe.command.application.dto.request;

import com.ohgiraffers.recipeservice.recipe.command.application.dto.RecipeIngredient;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.util.List;


@Getter
@Setter
@Schema(description = "레시피 등록 요청 DTO")
public class RecipeCreateRequest {
    @NotBlank(message = "요리 이름은 필수입니다.")
    @Schema(description = "요리 이름", example = "김치찌개")
    private String dishName;

    @NotNull(message = "재료는 필수입니다.")
    @Schema(description = "재료 목록")
    private List<RecipeIngredient> ingredients;

    @NotNull(message = "조리법은 필수입니다.")
    @Schema(description = "조리법 단계 목록", example = "[\"물을 끓인다\", \"김치를 넣는다\"]")
    private List<String> cookery;

}