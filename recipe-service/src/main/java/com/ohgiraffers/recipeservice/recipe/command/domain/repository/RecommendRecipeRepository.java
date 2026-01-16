package com.ohgiraffers.recipeservice.recipe.command.domain.repository;

import com.ohgiraffers.recipeservice.recipe.command.domain.aggregate.RecommendRecipe;

import java.util.Optional;



public interface RecommendRecipeRepository {

  RecommendRecipe save(RecommendRecipe Recipe);
  Optional<RecommendRecipe> findById(Integer id);
  void deleteById(Integer id);

}
