package com.ohgiraffers.recipeservice.recipe.query.mapper;


import com.ohgiraffers.recipeservice.recipe.query.dto.response.RecipeDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface RecipeMapper {

	/**
	 * 레시피 번호(PK)를 이용해 특정 레시피의 상세 정보를 조회합니다.
	 * @param recipeNo 레시피 번호
	 * @return 레시피 상세 정보 DTO
	 */
	RecipeDTO selectRecipeById(int recipeNo);

	/**
	 * 요리 번호를 기준으로 해당 요리에 포함된 모든 레시피(조리법) 목록을 조회합니다.
	 * @param dishNo 요리 번호
	 * @return 해당 요리의 레시피 DTO 리스트
	 */
	List<RecipeDTO> selectRecipeByDishId(@Param("dishNo") int dishNo);
}