package com.ohgiraffers.recipeservice.recipe.command.application.service;

import com.ohgiraffers.recipeservice.common.client.UserServiceClient;
import com.ohgiraffers.recipeservice.common.dto.ApiResponse;
import com.ohgiraffers.recipeservice.common.dto.UserDTO;
import com.ohgiraffers.recipeservice.common.dto.UserDetailResponse;
import com.ohgiraffers.recipeservice.recipe.command.application.dto.CookeryUtils;
import com.ohgiraffers.recipeservice.recipe.command.application.dto.RecipeIngredient;
import com.ohgiraffers.recipeservice.recipe.command.application.dto.request.RecipeCreateRequest;
import com.ohgiraffers.recipeservice.recipe.command.application.dto.request.RecipeRecommendRequest;
import com.ohgiraffers.recipeservice.recipe.command.application.dto.request.RecipeUpdateRequest;
import com.ohgiraffers.recipeservice.recipe.command.application.dto.response.RecommendRecipeResponse;
import com.ohgiraffers.recipeservice.recipe.command.domain.aggregate.Dish;
import com.ohgiraffers.recipeservice.recipe.command.domain.aggregate.Recipe;
import com.ohgiraffers.recipeservice.recipe.command.domain.aggregate.RecommendRecipe;
import com.ohgiraffers.recipeservice.recipe.command.domain.repository.DishRepository;
import com.ohgiraffers.recipeservice.recipe.command.domain.repository.RecipeRepository;
import com.ohgiraffers.recipeservice.recipe.command.domain.repository.RecommendRecipeRepository;
import com.ohgiraffers.recipeservice.recipe.command.infrastructure.service.RecipeRecommendService;
import com.ohgiraffers.recipeservice.recipe.query.dto.response.RecipeDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeCommandService {

	private final DishRepository dishRepository;
	private final UserServiceClient userServiceClient;
	private final ModelMapper modelMapper;
	private final RecipeRecommendService recipeRecommendService;
	private final RecommendRecipeRepository recommendRecipeRepository;
	private final RecipeRepository recipeRepository;

	@Transactional
	public Integer registRecipe(RecipeCreateRequest request) { // username 파라미터 추가

		// 1. Dish 조회
		Dish dish = dishRepository.findByDishName(request.getDishName())
				.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 음식입니다."));

		// 2. Recipe 생성
		Recipe savedRecipe = Recipe.builder()
				.dish(dish)
				.recipeIngredient(RecipeIngredient.listToString(request.getIngredients()))
				.recipeCookery(CookeryUtils.listToString(request.getCookery()))
				.build();

		return recipeRepository.save(savedRecipe).getId();
	}

	@Transactional
	public RecipeDTO updateRecipe(RecipeUpdateRequest request) {

		// Recipe Entity
		Recipe updatedRecipe = recipeRepository.findById(request.getRecipeNo())
				.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 레시피입니다."));

		// 재료 수정
		List<RecipeIngredient> ingredients = request.getIngredients();
		if (ingredients != null)
			updatedRecipe.setRecipeIngredient(RecipeIngredient.listToString(request.getIngredients()));

		// 조리법 수정
		List<String> cookery = request.getCookery();
		if (cookery != null)
			updatedRecipe.setRecipeCookery(CookeryUtils.listToString(request.getCookery()));

		return modelMapper.map(updatedRecipe, RecipeDTO.class);
	}

	@Transactional
	public void deleteRecipe(Integer dishNo) {
		recipeRepository.deleteById(dishNo);
	}

	@Transactional
	public RecommendRecipeResponse getRecipeRecommendation(RecipeRecommendRequest request, String username) {
		// 1. AI 추천 서비스 호출
		RecommendRecipeResponse recipeRecommendation = recipeRecommendService.getRecipeRecommendation(request);

		// 2. 추천 결과 저장용 엔티티 변환
		RecommendRecipe recommendRecipe = modelMapper.map(recipeRecommendation, RecommendRecipe.class);

		// 3. Feign client로 유저 조회
		Long userNo = getUserNoByUserId(username);

		// 4. RecommendRecipe 엔티티의 userNo 필드 타입에 맞게 설정 (int로 가정)
		recommendRecipe.setUserNo(userNo.intValue());

		recommendRecipeRepository.save(recommendRecipe);
		return recipeRecommendation;
	}

	@Transactional
	public RecipeDTO saveRecommendedToMyRecipe(Integer recommendRecipeNo, Integer dishNo) {
		// 음식 조회
		Dish dish = dishRepository.findById(dishNo)
				.orElseThrow(() -> new IllegalArgumentException("음식이 조회되지 않습니다"));
		// 추천레시피 조회
		RecommendRecipe rcdRecipe = recommendRecipeRepository.findById(recommendRecipeNo)
				.orElseThrow(() -> new IllegalArgumentException("추천레시피 데이터 없음"));
		return RecipeDTO.from(
				recipeRepository.save(
						Recipe.builder()
								.dish(dish)
								.recipeIngredient(rcdRecipe.getRcdRecipeIngredients())
								.recipeCookery(rcdRecipe.getRcdRecipeCookery())
								.build())
				);
	}

	private Long getUserNoByUserId(String userId) {
		ApiResponse<UserDetailResponse> response = userServiceClient.getUserByUserId(userId);
		if (response == null || !response.getSuccess() || response.getData() == null) {
			throw new IllegalArgumentException("사용자를 찾을 수 없습니다.");
		}
		UserDTO user = response.getData().getUser();
		if (user == null) {
			throw new IllegalArgumentException("사용자를 찾을 수 없습니다.");
		}
		return user.getUserNo();
	}
}
