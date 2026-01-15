package com.ohgiraffers.mainservice.recipe.command.infrastructure.repository;


import com.ohgiraffers.mainservice.recipe.command.domain.aggregate.RecommendRecipe;
import com.ohgiraffers.mainservice.recipe.command.domain.repository.RecommendRecipeRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaRecipeRecommendRepository extends RecommendRecipeRepository, JpaRepository<RecommendRecipe,Integer> {
}
