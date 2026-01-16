package com.ohgiraffers.recipeservice.recipe.query.service;



import com.ohgiraffers.recipeservice.recipe.query.dto.response.*;
import com.ohgiraffers.recipeservice.recipe.query.mapper.DishCategoryMapper;
import com.ohgiraffers.recipeservice.recipe.query.mapper.DishMapper;
import com.ohgiraffers.recipeservice.recipe.query.mapper.RecipeMapper;
import com.ohgiraffers.recipeservice.recipe.query.mapper.RecommendRecipeMapper;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecipeQueryService {

	private final DishCategoryMapper dishCategoryMapper;
	private final DishMapper dishMapper;
	private final RecipeMapper recipeMapper;
	private final RecommendRecipeMapper recommendRecipeMapper; // Added

	public List<DishCategoryDTO> findAllCategories() {
		return dishCategoryMapper.selectAllDishCategories();
	}

	public List<DishDTO> findDishesByUserNo(int userno) {
		return dishMapper.selectDishesByUser(userno);
	}

	public RecipeDetailResponse getRecipeDetail(int dishNo) {
		DishDTO dish = dishMapper.selectDishById(dishNo)
				.orElseThrow(() -> new RuntimeException("해당 아이디의 음식을 찾을 수 없습니다."));
		List<RecipeDTO> recipes = recipeMapper.selectRecipeByDishId(dishNo);

		return new RecipeDetailResponse(dish, recipes);
	}

	public List<DishDTO> findDishesByUsername(String username) {
		return dishMapper.selectDishesByUsername(username);
	}

	public List<RecipeDetailResponse> findDetailsByUser(int userNo) {
		List<DishDTO> dishes = dishMapper.selectDishesByUser(userNo);
		return dishes.stream()
				.map(dish -> {
					List<RecipeDTO> recipes = recipeMapper.selectRecipeByDishId(dish.getDishNo());
					return new RecipeDetailResponse(dish, recipes);
				})
				.collect(Collectors.toList());
	}

	public List<RecommendRecipeDTO> findRecommendRecipesByUser(int userNo) {
		return recommendRecipeMapper.selectRecommendRecipesByUser(userNo);
	}
}