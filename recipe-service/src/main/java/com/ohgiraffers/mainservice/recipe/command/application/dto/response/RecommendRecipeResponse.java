package com.ohgiraffers.mainservice.recipe.command.application.dto.response;

import com.ohgiraffers.mainservice.recipe.command.application.dto.DishCategoryEnum;
import com.ohgiraffers.mainservice.recipe.command.application.dto.RecipeIngredient;
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
  public static RecommendRecipeResponse createDummyResponse() {

    // 1. 대체 재료 목록 생성
    List<Substitution> dummySubstitutions = List.of(
        Substitution.builder().original("설탕").replacement("올리고당").build(),
        Substitution.builder().original("고추장").replacement("고춧가루와 간장").build()
    );

    // 2. 재료 목록 생성 (RecipeIngredient의 필드가 name, amount라고 가정)
    List<RecipeIngredient> dummyIngredients = List.of(
        new RecipeIngredient("돼지고기 앞다리살", 300,"g"),
        new RecipeIngredient("양파", 0.5,"g"),
        new RecipeIngredient("대파", 0.5,"g")
    );

    // 3. 전체 응답 객체 생성

	  // 이제 response 객체를 사용하시면 됩니다.
    return RecommendRecipeResponse.builder()
        .dishCategoryEnum(DishCategoryEnum.KOREAN) // Enum 상수는 실제 정의된 것을 사용하세요
        .dishName("제육볶음")
        .ingredients(dummyIngredients)
        .substitutions(dummySubstitutions)
        .cookery(List.of(
            "돼지고기를 먹기 좋은 크기로 썰어 양념에 재웁니다.",
            "팬을 달구고 고기를 먼저 볶습니다.",
            "고기가 익기 시작하면 손질한 채소를 넣고 함께 볶습니다.",
            "마지막으로 강불에서 빠르게 볶아 불향을 입힙니다."
        ))
        .tips(List.of(
            "센 불에 볶아야 육즙이 빠져나가지 않고 맛있습니다.",
            "고기를 미리 설탕에 버무려두면 육질이 부드러워집니다."
        ))
        .build();
  }
}