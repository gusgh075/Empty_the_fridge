package com.ohgiraffers.recipeservice.recipe.query.mapper;



import com.ohgiraffers.recipeservice.recipe.query.dto.response.DishCategoryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface DishCategoryMapper {

	/**
	 * 카테고리 번호(PK)를 이용해 단일 요리 카테고리 정보를 조회합니다.
	 * @param dishCategoryNo 요리 카테고리 번호
	 * @return 요리 카테고리 상세 정보 DTO
	 */
	Optional<DishCategoryDTO> selectDishCategoryById(int dishCategoryNo);

	/**
	 * 등록된 모든 요리 카테고리 목록을 조회합니다.
	 * @return 전체 요리 카테고리 DTO 리스트
	 */
	List<DishCategoryDTO> selectAllDishCategories();
}