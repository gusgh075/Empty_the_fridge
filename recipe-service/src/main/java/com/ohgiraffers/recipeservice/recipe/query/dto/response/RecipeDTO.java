package com.ohgiraffers.recipeservice.recipe.query.dto.response;

import com.ohgiraffers.recipeservice.recipe.command.domain.aggregate.Recipe;
import io.swagger.v3.oas.annotations.media.Schema;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "레시피 정보 응답 DTO")
public class RecipeDTO {
    @Schema(description = "레시피 번호", example = "1")
    private Integer recipeNo;
    @Schema(description = "요리 번호", example = "10")
    private Integer dishNo;
    @Schema(description = "레시피 재료 (JSON 문자열)", example = "{\"당근\": \"1개\", \"양파\": \"0.5개\"}")
    private String recipeIngredient;
    @Schema(description = "레시피 조리법 (문자열)", example = "1. 물을 끓인다.\n2. 재료를 넣는다.")
    private String recipeCookery;
    @Schema(description = "생성 일시", example = "2023-10-10T10:00:00")
    private LocalDateTime createdAt;
    @Schema(description = "수정 일시", example = "2023-10-10T12:00:00")
    private LocalDateTime updatedAt;

    public static RecipeDTO from(Recipe recipe) {
        return RecipeDTO.builder()
                .recipeNo(recipe.getId())
                .dishNo(recipe.getDish().getDishNo())
                .recipeIngredient(recipe.getRecipeIngredient())
                .recipeCookery(recipe.getRecipeCookery())
                .createdAt(recipe.getCreatedAt())
                .updatedAt(recipe.getUpdatedAt())
                .build();
    }
}