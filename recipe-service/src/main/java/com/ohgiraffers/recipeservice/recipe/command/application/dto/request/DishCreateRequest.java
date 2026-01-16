package com.ohgiraffers.recipeservice.recipe.command.application.dto.request;

import com.ohgiraffers.recipeservice.recipe.command.application.dto.DishCategoryEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Schema(description = "요리 등록 요청 DTO")
public class DishCreateRequest {
    @Schema(description = "요리 이름", example = "김치찌개")
    private String dishName;
    @Schema(description = "요리 카테고리", example = "KOREAN")
    private DishCategoryEnum dishCategoryEnum;
}
