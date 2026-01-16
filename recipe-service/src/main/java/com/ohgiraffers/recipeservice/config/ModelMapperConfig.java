package com.ohgiraffers.recipeservice.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration()
			.setMatchingStrategy(MatchingStrategies.STRICT)
			.setFieldAccessLevel(
				org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
			.setFieldMatchingEnabled(true);
		return modelMapper;
	}

	/**
	 * RecommendRecipe 전용 매핑 설정
	 */
//	private void configureRecommendRecipeMapping(ModelMapper modelMapper) {
//
//		// 1. DishCategory(Enum) -> dishCategoryNo(Integer) 변환 컨버터
//		// 순서(Ordinal)에 1을 더해 번호로 매핑
//		Converter<com.ohgiraffers.mainservice.recipe.command.application.dto.DishCategoryEnum, Integer> categoryConverter = context ->
//			context
//				.getSource() == null ? null : context.getSource().ordinal() + 1;
//
//		// 2. Ingredients List -> String 변환 컨버터
//		Converter<List<RecipeIngredient>, String> ingredientsConverter = context -> context
//			.getSource() == null ? null
//			: RecipeIngredient.listToString(context.getSource());
//
//		// 3. Substitutions List -> String 변환 컨버터
//		// 예: "고기 -> 버섯, 설탕 -> 올리고당" 형태
//		Converter<List<RecommendRecipeResponse.Substitution>, String> substitutionConverter = context -> context
//			.getSource() == null ? null
//			: context.getSource().stream()
//			.map(sub -> String.format("%s -> %s", sub.getOriginal(),
//				sub.getReplacement()))
//			.collect(Collectors.joining(", "));
//
//		// 4. Cookery & Tips List -> String 변환 컨버터 (줄바꿈으로 구분)
//		Converter<List<String>, String> listToStringConverter = context -> context.getSource() == null ? null
//			: String.join("\n", context.getSource());
//
//		// TypeMap 설정
//		modelMapper.createTypeMap(RecommendRecipeResponse.class, RecommendRecipe.class)
//			.addMappings(mapper -> {
//				// (1) 카테고리 Enum -> 번호
//				mapper.using(categoryConverter)
//					.map(RecommendRecipeResponse::getDishCategoryEnum,
//						RecommendRecipe::setDishCategoryNo);
//
//				// (2) 재료 리스트 -> 문자열
//				mapper.using(ingredientsConverter)
//					.map(RecommendRecipeResponse::getIngredients,
//						RecommendRecipe::setRcdRecipeIngredients);
//
//				// (3) 대체 재료 리스트 -> 문자열
//				mapper.using(substitutionConverter)
//					.map(RecommendRecipeResponse::getSubstitutions,
//						RecommendRecipe::setRcdRecipeSubstitutions);
//
//				// (4) 조리법 리스트 -> 문자열
//				mapper.using(listToStringConverter)
//					.map(RecommendRecipeResponse::getCookery,
//						RecommendRecipe::setRcdRecipeCookery);
//
//				// (5) 팁 리스트 -> 문자열
//				mapper.using(listToStringConverter)
//					.map(RecommendRecipeResponse::getTips,
//						RecommendRecipe::setRcdRecipeTips);
//
//				// (6) 요리이름 매핑
//				mapper.map(RecommendRecipeResponse::getDishName,
//					RecommendRecipe::setRcdRecipeDishName);
//
//				// (7) 사용자 번호: 서비스 로직에서 직접 세팅하므로 매핑 제외
//				mapper.skip(RecommendRecipe::setUserNo);
//			});
//	}

	/**
	 * RecipeDTO와 Recipe Entity 간의 매핑을 설정하는 메서드
	 */
//	private void configureRecipeMapping(ModelMapper modelMapper) {
//
//		// 1. Integer(dishNo)를 Dish 엔티티 객체로 변환하는 컨버터 정의
//		Converter<Integer, Dish> dishConverter = context -> {
//			if (context.getSource() == null)
//				return null;
//
//			// 새로운 Dish 객체를 생성하여 PK 값만 설정
//			Dish dish = new Dish();
//			dish.setDishNo(context.getSource());
//			return dish;
//		};
//
//		// 2. RecipeDTO -> Recipe (Entity) 타입맵 생성 및 매핑 추가
//		modelMapper.createTypeMap(RecipeDTO.class, Recipe.class)
//			.addMappings(mapper -> {
//				// DTO의 recipeNo -> Entity의 id (필드명 불일치 해결)
//				mapper.map(RecipeDTO::getRecipeNo, Recipe::setId);
//
//				// DTO의 Integer dishNo -> Entity의 Dish dishNo (타입 불일치 해결)
//				mapper.using(dishConverter).map(RecipeDTO::getDishNo, Recipe::setDish);
//
//				// 나머지 필드(recipeIngredient, recipeCookery 등)는 이름이 같으므로 자동 매핑됩니다.
//			});
//
//		// 3. (필요 시) Recipe (Entity) -> RecipeDTO 매핑 설정 (조회용)
//		modelMapper.createTypeMap(Recipe.class, RecipeDTO.class)
//			.addMappings(mapper -> {
//				// Entity의 id -> DTO의 recipeNo
//				mapper.map(Recipe::getId, RecipeDTO::setRecipeNo);
//
//				// Entity의 Dish 객체 내부의 dishNo(int) -> DTO의 dishNo(Integer)
//				mapper.map(src -> src.getDish().getDishNo(), RecipeDTO::setDishNo);
//			});
//	}

}