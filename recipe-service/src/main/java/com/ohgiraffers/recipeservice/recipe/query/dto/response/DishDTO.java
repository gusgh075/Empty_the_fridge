package com.ohgiraffers.recipeservice.recipe.query.dto.response;

import com.ohgiraffers.recipeservice.recipe.command.domain.aggregate.Dish;
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
@Schema(description = "요리 정보 응답 DTO")
public class DishDTO {
    @Schema(description = "요리 번호", example = "10")
    private Integer dishNo;
    @Schema(description = "등록자 번호", example = "5")
    private Integer userNo;
    @Schema(description = "카테고리 번호", example = "1")
    private Integer dishCategoryNo;
    @Schema(description = "요리 이름", example = "김치찌개")
    private String dishName;
    @Schema(description = "요리 이미지 경로")
    private String dishImgFileRoute;
    @Schema(description = "마크 여부", example = "false")
    private boolean dishIsMarked;
    @Schema(description = "생성 일시", example = "2023-10-10T10:00:00")
    private LocalDateTime createdAt;
    @Schema(description = "수정 일시", example = "2023-10-10T12:00:00")
    private LocalDateTime updatedAt;

    public static DishDTO from(Dish dish) {
        return DishDTO.builder()
                .dishNo(dish.getDishNo())
                .userNo(dish.getUserNo().intValue())
                .dishCategoryNo(dish.getDishCategoryNo().getId())
                .dishName(dish.getDishName())
                .dishImgFileRoute(dish.getDishImgFileRoute())
                .dishIsMarked(dish.getDishIsMarked())
                .createdAt(dish.getCreatedAt())
                .updatedAt(dish.getUpdatedAt())
                .build();
    }
}