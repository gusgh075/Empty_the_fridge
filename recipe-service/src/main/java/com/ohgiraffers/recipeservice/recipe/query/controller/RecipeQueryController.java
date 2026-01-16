package com.ohgiraffers.recipeservice.recipe.query.controller;


import com.ohgiraffers.recipeservice.common.dto.ApiResponse;
import com.ohgiraffers.recipeservice.recipe.query.dto.response.DishCategoryDTO;
import com.ohgiraffers.recipeservice.recipe.query.dto.response.DishDTO;
import com.ohgiraffers.recipeservice.recipe.query.dto.response.RecipeDetailResponse;
import com.ohgiraffers.recipeservice.recipe.query.dto.response.RecommendRecipeDTO;
import com.ohgiraffers.recipeservice.recipe.query.service.RecipeQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 레시피 조회 전용 REST API 컨트롤러
 */
@RestController
@RequestMapping("/recipes")
@RequiredArgsConstructor
@Tag(name = "Recipe Query API", description = "요리(Dish) 및 레시피(Recipe) 조회 관련 API")
public class RecipeQueryController {

  private final RecipeQueryService recipeQueryService;

  /**
   * 전체 요리 카테고리 목록을 조회합니다.
   * GET /api/v1/recipes/categories
   */
  @Operation(summary = "전체 요리 카테고리 조회", description = "시스템에 등록된 모든 요리 카테고리 목록을 조회합니다.")
  @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "카테고리 목록 조회 성공")
  @GetMapping("/categories")
  public ResponseEntity<ApiResponse<List<DishCategoryDTO>>> getAllCategories() {
    List<DishCategoryDTO> categories = recipeQueryService.findAllCategories();
    return ResponseEntity.ok(ApiResponse.success(categories));
  }

  /**
   * 현재 로그인한 사용자가 등록한 요리 목록을 조회합니다. (내 레시피 조회)
   * RecipeCommandController의 인증 방식을 적용하여 경로 변수 대신 토큰 정보를 활용합니다.
   * GET /api/v1/recipes/my
   */
  @Operation(summary = "내 요리 목록 조회", description = "로그인한 사용자가 등록한 요리 목록을 조회합니다.")
  @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "내 요리 목록 조회 성공")
  @GetMapping("/my")
  public ResponseEntity<ApiResponse<List<DishDTO>>> getMyDishes(
      @AuthenticationPrincipal String userNo) {

    // UserPrincipal에서 PK(userNo)를 꺼내 서비스로 전달
    List<DishDTO> dishes = recipeQueryService.findDishesByUsername(userNo);
    return ResponseEntity.ok(ApiResponse.success(dishes));
  }

  /**
   * 특정 사용자가 등록한 요리 목록을 조회합니다. (일반 조회)
   * GET /api/v1/recipes/users/{userNo}
   */
  @Operation(summary = "특정 사용자 요리 목록 조회", description = "특정 사용자가 등록한 요리 목록을 조회합니다.")
  @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "사용자 요리 목록 조회 성공")
  @GetMapping("/users/{userNo}")
  public ResponseEntity<ApiResponse<List<DishDTO>>> getDishesByUser(
      @Parameter(description = "사용자 번호") @PathVariable int userNo) {
    List<DishDTO> dishes = recipeQueryService.findDishesByUserNo(userNo);
    return ResponseEntity.ok(ApiResponse.success(dishes));
  }

  /**
   * 특정 사용자가 등록한 요리의 상세 정보(레시피 포함)를 모두 조회합니다.
   * GET /api/v1/recipes/users/{userNo}/details
   */
  @Operation(summary = "특정 사용자 요리 상세 정보 목록 조회", description = "특정 사용자가 등록한 모든 요리의 상세 정보(레시피 포함)를 조회합니다.")
  @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "사용자 요리 상세 정보 목록 조회 성공")
  @GetMapping("/users/{userNo}/details")
  public ResponseEntity<ApiResponse<List<RecipeDetailResponse>>> getDishDetailsByUser(
      @Parameter(description = "사용자 번호") @PathVariable int userNo) {
    List<RecipeDetailResponse> response = recipeQueryService.findDetailsByUser(userNo);
    return ResponseEntity.ok(ApiResponse.success(response));
  }

  /**
   * 특정 사용자의 추천 레시피(rcd_recipe) 목록을 조회합니다.
   * GET /api/v1/recipes/recommends/users/{userNo}
   */
  @Operation(summary = "특정 사용자 추천 레시피 목록 조회", description = "특정 사용자가 추천받은 레시피 목록을 조회합니다.")
  @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "추천 레시피 목록 조회 성공")
  @GetMapping("/recommends/users/{userNo}")
  public ResponseEntity<ApiResponse<List<RecommendRecipeDTO>>> getRecommendRecipesByUser(
      @Parameter(description = "사용자 번호") @PathVariable int userNo) {
    List<RecommendRecipeDTO> response = recipeQueryService.findRecommendRecipesByUser(userNo);
    return ResponseEntity.ok(ApiResponse.success(response));
  }

  /**
   * 특정 요리의 상세 정보와 조리법(레시피) 목록을 함께 조회합니다.
   * GET /api/v1/recipes/{dishNo}
   */
  @Operation(summary = "요리 상세 정보 조회", description = "특정 요리의 상세 정보와 레시피를 조회합니다.")
  @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "요리 상세 정보 조회 성공")
  @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "요리를 찾을 수 없음")
  @GetMapping("/{dishNo}")
  public ResponseEntity<ApiResponse<RecipeDetailResponse>> getRecipeDetail(
      @Parameter(description = "요리 번호") @PathVariable int dishNo) {
    RecipeDetailResponse detail = recipeQueryService.getRecipeDetail(dishNo);

    if (detail.getDish() == null) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(ApiResponse.success(detail));
  }
}