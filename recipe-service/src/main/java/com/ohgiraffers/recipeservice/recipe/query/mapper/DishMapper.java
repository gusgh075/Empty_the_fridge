package com.ohgiraffers.recipeservice.recipe.query.mapper;



import com.ohgiraffers.recipeservice.recipe.query.dto.response.DishDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface DishMapper {

	/**
	 * 요리 번호(PK)를 이용해 특정 요리의 상세 정보를 조회합니다.
	 * @param dishNo 요리 번호
	 * @return 요리 상세 정보 DTO
	 */
	Optional<DishDTO> selectDishById(int dishNo);

	/**
	 * 특정 사용자 번호를 기준으로 해당 사용자가 등록한 모든 요리 목록을 조회합니다.
	 * @param userNo 사용자 번호
	 * @return 해당 사용자의 요리 DTO 리스트
	 */
	List<DishDTO> selectDishesByUser(@Param("userNo") int userNo);

	List<DishDTO> selectDishesByUsername(@Param("username") String username);
}