package com.ohgiraffers.recipeservice.recipe.command.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "요리 카테고리 (KOREAN, CHINESE, JAPANESE, WESTERN, SNACK, SALAD, FUSION, ETC)")
public enum DishCategoryEnum {
  KOREAN, CHINESE, JAPANESE, WESTERN, SNACK, SALAD, FUSION, ETC
}