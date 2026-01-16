package com.ohgiraffers.recipeservice.recipe.query.mapper;


import com.ohgiraffers.recipeservice.recipe.query.dto.response.RecommendRecipeDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface RecommendRecipeMapper {

    /**
     * 특정 사용자의 추천 레시피(rcd_recipe) 목록을 조회합니다.
     * 
     * @param userNo 사용자 번호
     * @return 추천 레시피 DTO 리스트
     */
    List<RecommendRecipeDTO> selectRecommendRecipesByUser(@Param("userNo") int userNo);

    /**
     * 추천 레시피 번호로 조회합니다.
     * 
     * @param id 추천 레시피 PK
     * @return 추천 레시피 DTO
     */
    Optional<RecommendRecipeDTO> selectRecommendRecipeById(int id);
}
