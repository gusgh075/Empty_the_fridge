package com.ohgiraffers.recipeservice.recipe.query.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Schema(description = "요리 카테고리 응답 DTO")
public class DishCategoryDTO {
    @Schema(description = "카테고리 번호", example = "1")
    private Integer dishCategoryNo;
    @Schema(description = "카테고리 이름", example = "KOREAN")
    private String dishCategoryName;
    @Schema(description = "생성 일시", example = "2023-10-10T10:00:00")
    private LocalDateTime createdAt;
    @Schema(description = "수정 일시", example = "2023-10-10T12:00:00")
    private LocalDateTime updatedAt;
}