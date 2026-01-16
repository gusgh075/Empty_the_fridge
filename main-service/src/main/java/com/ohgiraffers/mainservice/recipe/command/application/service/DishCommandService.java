package com.ohgiraffers.recipeservice.recipe.command.application.service;

import java.util.List;
import java.util.Optional;

import com.ohgiraffers.recipeservice.common.client.UserServiceClient;
import com.ohgiraffers.recipeservice.common.dto.ApiResponse;
import com.ohgiraffers.recipeservice.common.dto.UserDTO;
import com.ohgiraffers.recipeservice.common.dto.UserDetailResponse;
import com.ohgiraffers.recipeservice.recipe.command.application.dto.request.DishCreateRequest;
import com.ohgiraffers.recipeservice.recipe.command.application.dto.request.DishUpdateRequest;
import com.ohgiraffers.recipeservice.recipe.command.domain.aggregate.Dish;
import com.ohgiraffers.recipeservice.recipe.command.domain.aggregate.DishCategory;
import com.ohgiraffers.recipeservice.recipe.command.domain.aggregate.Recipe;
import com.ohgiraffers.recipeservice.recipe.command.domain.aggregate.RecommendRecipe;
import com.ohgiraffers.recipeservice.recipe.command.domain.repository.DishCategoryRepository;
import com.ohgiraffers.recipeservice.recipe.command.domain.repository.DishRepository;
import com.ohgiraffers.recipeservice.recipe.command.domain.repository.RecipeRepository;
import com.ohgiraffers.recipeservice.recipe.command.domain.repository.RecommendRecipeRepository;
import com.ohgiraffers.recipeservice.recipe.query.dto.response.DishDTO;
import org.springframework.stereotype.Service;



import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DishCommandService {

	private final RecommendRecipeRepository recommendRecipeRepository;
	private final DishRepository dishRepository;
	private final UserServiceClient userServiceClient;
	private final DishCategoryRepository dishCategoryRepository;
	private final RecipeRepository recipeRepository;

	@Transactional
	public DishDTO saveRecommendedToMyDish(Integer recommendRecipeNo, String username) {
		DishDTO resultDish;
		// 추천레시피 조회
		RecommendRecipe rcdRecipe = recommendRecipeRepository.findById(recommendRecipeNo)
			.orElseThrow(() -> new IllegalArgumentException("추천레시피 데이터 없음"));

		// Feign client로 유저 조회
		Long userNo = getUserNoByUserId(username);

		// 추천레시피의 이름으로 음식이 있는지 조회
		Optional<Dish> dish = dishRepository.findByDishName(rcdRecipe.getRcdRecipeDishName());
		// 음식이 없을 때 등록
		if (dish.isEmpty())
			resultDish = DishDTO.from(
				dishRepository.save(
					Dish.builder()
						.userNo(userNo)
						.dishCategoryNo(dishCategoryRepository.findById(rcdRecipe.getDishCategoryNo())
							.orElseThrow(() -> new IllegalArgumentException("카테고리 없음")))
						.dishName(rcdRecipe.getRcdRecipeDishName())
						.build())
			);
			// 음식이 있을 때 get
		else
			resultDish = DishDTO.from(dish.get());
		return resultDish;
	}

	@Transactional
	public void registDish(
		DishCreateRequest request,
		String username) {

		// 1. Feign client로 유저 조회
		Long userNo = getUserNoByUserId(username);

		// 2. 카테고리 조회
		DishCategory category = dishCategoryRepository
			.findById(request.getDishCategoryEnum().ordinal() + 1)
			.orElseThrow(() -> new IllegalArgumentException("해당 카테고리를 찾을 수 없습니다."));

		// 3. Dish 엔티티 생성
		Dish newDish = Dish.builder()
			.userNo(userNo)
			.dishCategoryNo(category)
			.dishName(request.getDishName())
			.dishImgFileRoute(null)
			.recipes(null)
			.build();

		dishRepository.save(newDish);
	}

	@Transactional
	public void updateDish(
		DishUpdateRequest request,
		String username) {
		// 1. Dish 조회
		Dish dish = dishRepository.findById(request.getDishNo())
			.orElseThrow(() -> new IllegalArgumentException("해당 요리를 찾을 수 없습니다."));

		// 2. Feign client로 유저 조회 및 권한 확인
		Long userNo = getUserNoByUserId(username);
		if (!dish.getUserNo().equals(userNo)) {
			throw new IllegalArgumentException("수정 권한이 없습니다.");
		}

		// 3. 수정 (Dirty Checking)
		if (request.getDishName() != null) {
			dish.setDishName(request.getDishName());
		}
		if (request.getDishCategoryEnum() != null) {
			DishCategory category = dishCategoryRepository.findById(request.getDishCategoryEnum().ordinal() + 1)
				.orElseThrow(() -> new IllegalArgumentException("해당 카테고리를 찾을 수 없습니다."));
			dish.setDishCategoryNo(category);
		}
	}

	@Transactional
	public void deleteDish(Integer dishNo, String username) {
		// 1. Dish 조회
		Dish dish = dishRepository.findById(dishNo)
			.orElseThrow(() -> new IllegalArgumentException("해당 요리를 찾을 수 없습니다."));

		// 2. Feign client로 유저 조회 및 권한 확인
		Long userNo = getUserNoByUserId(username);
		if (!dish.getUserNo().equals(userNo)) {
			throw new IllegalArgumentException("삭제 권한이 없습니다.");
		}

		// 3. 연관된 Recipe 삭제
		List<Recipe> recipes = recipeRepository
			.findByDish_DishNo(dish.getDishNo());
		recipeRepository.deleteAll(recipes);

		// 4. Dish 삭제
		dishRepository.deleteById(dish.getDishNo());
	}

	private Long getUserNoByUserId(String userId) {
		ApiResponse<UserDetailResponse> response = userServiceClient.getUserByUserId(userId);
		if (response == null || !response.getSuccess() || response.getData() == null) {
			throw new IllegalArgumentException("해당 유저를 찾을 수 없습니다.");
		}
		UserDTO user = response.getData().getUser();
		if (user == null) {
			throw new IllegalArgumentException("해당 유저를 찾을 수 없습니다.");
		}
		return user.getUserNo();
	}
}
