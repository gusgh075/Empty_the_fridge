package com.ohgiraffers.recipeservice.recipe.command.application.controller;



import com.ohgiraffers.recipeservice.common.dto.ApiResponse;
import com.ohgiraffers.recipeservice.recipe.command.application.dto.request.RecipeCreateRequest;
import com.ohgiraffers.recipeservice.recipe.command.application.dto.request.RecipeRecommendRequest;
import com.ohgiraffers.recipeservice.recipe.command.application.dto.request.RecipeUpdateRequest;
import com.ohgiraffers.recipeservice.recipe.command.application.dto.response.AdoptRecommendedResponse;
import com.ohgiraffers.recipeservice.recipe.command.application.dto.response.RecommendRecipeResponse;
import com.ohgiraffers.recipeservice.recipe.command.application.service.DishCommandService;
import com.ohgiraffers.recipeservice.recipe.command.application.service.RecipeCommandService;
import com.ohgiraffers.recipeservice.recipe.query.dto.response.DishDTO;
import com.ohgiraffers.recipeservice.recipe.query.dto.response.RecipeDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

@RestController
@RequestMapping("/api/v1/recipes")
@RequiredArgsConstructor
@Tag(name = "Recipe Command API", description = "레시피 등록, 수정, 삭제, 추천 관련 API")
public class RecipeCommandController {

	private final RecipeCommandService recipeCommandService;
	private final DishCommandService dishCommandService;

	/**
	 * 레시피 신규 등록
	 * POST /api/v1/recipes
	 *
	 */
	@Operation(summary = "레시피 등록", description = "새로운 레시피를 등록합니다.")
	@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "레시피 등록 성공")
	@PostMapping
	public ResponseEntity<ApiResponse<Integer>> registRecipe(
			@RequestBody @Valid RecipeCreateRequest request) {

		Integer recipeNo = recipeCommandService.registRecipe(request);

		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(ApiResponse.success(recipeNo));
	}

	/**
	 * 레시피 수정
	 * PUT /api/v1/recipes/{dishNo}
	 */
	@Operation(summary = "레시피 수정", description = "기존 레시피를 수정합니다.")
	@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "레시피 수정 성공")
	@PutMapping("/update")
	public ResponseEntity<ApiResponse<RecipeDTO>> updateRecipe(
			@RequestBody @Valid RecipeUpdateRequest request) { // 수정 시에도 본인 확인이 필요할 수 있어 추가 권장

		// 서비스 메서드에 username을 전달하여 본인 확인 로직 추가 가능 (현재는 기존 로직 유지하되 확장성 고려)
		RecipeDTO recipeDTO = recipeCommandService.updateRecipe(request);

		return ResponseEntity.ok(ApiResponse.success(recipeDTO));
	}

	/**
	 * 레시피 삭제
	 * DELETE /api/v1/recipes/delete
	 */
	@Operation(summary = "레시피 삭제", description = "레시피를 삭제합니다.")
	@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "레시피 삭제 성공")
	@DeleteMapping("/delete")
	public ResponseEntity<ApiResponse<Void>> deleteRecipe(
			@Parameter(description = "레시피 번호") @RequestParam Integer recipeNo) {

		recipeCommandService.deleteRecipe(recipeNo);

		return ResponseEntity.ok(ApiResponse.success(null));
	}

	/**
	 * 레시피 추천
	 * 
	 * @param request 레시피 추천 객체
	 * @return 레시피 추천 결과
	 */
	@Operation(summary = "레시피 추천", description = "AI를 이용하여 레시피를 추천받습니다.")
	@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "레시피 추천 성공")
	@PostMapping("/recommend")
	public ResponseEntity<ApiResponse<RecommendRecipeResponse>> recommendRecipe(
			@RequestBody RecipeRecommendRequest request,
			@AuthenticationPrincipal UserDetails userDetails) {

		// userDetails.getUsername()을 통해 로그인한 사용자 ID 전달
		RecommendRecipeResponse response = recipeCommandService.getRecipeRecommendation(request,
				userDetails.getUsername());
		return ResponseEntity.ok(ApiResponse.success(response));
	}

	/**
	 * 추천 레시피를 유저 레시피로 등록
	 * 
	 * @param recommendRecipeNo 추천 레시피 번호
	 * @param userDetails       유저 정보
	 * @return 저장된 recipe와 dish 정보
	 */
	@Operation(summary = "추천 레시피 저장", description = "추천받은 레시피를 내 레시피로 저장합니다.")
	@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "추천 레시피 저장 성공")
	@GetMapping("/recommend/save")
	public ResponseEntity<ApiResponse<AdoptRecommendedResponse>> adoptRecommendedRecipe(
			@Parameter(description = "추천 레시피 번호") @RequestParam Integer recommendRecipeNo,
			@AuthenticationPrincipal UserDetails userDetails) {
		DishDTO responseDish = dishCommandService.saveRecommendedToMyDish(recommendRecipeNo, userDetails.getUsername());
		RecipeDTO responseRecipe = recipeCommandService.saveRecommendedToMyRecipe(recommendRecipeNo,
				responseDish.getDishNo());
		AdoptRecommendedResponse adoptRecommendedResponse = new AdoptRecommendedResponse(responseRecipe, responseDish);
		return ResponseEntity.ok(ApiResponse.success(adoptRecommendedResponse));
	}
}