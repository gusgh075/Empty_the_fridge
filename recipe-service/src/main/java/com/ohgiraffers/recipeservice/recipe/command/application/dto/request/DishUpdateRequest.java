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
@Schema(description = "요리 수정 요청 DTO")
public class DishUpdateRequest {
    @Schema(description = "요리 번호", example = "1")
    private Integer dishNo;
    @Schema(description = "요리 이름", example = "된장찌개")
    private String dishName;
    @Schema(description = "요리 카테고리", example = "KOREAN")
    private DishCategoryEnum dishCategoryEnum;
}
