package com.ohgiraffers.recipeservice.recipe.command.infrastructure.repository;



import com.ohgiraffers.recipeservice.recipe.command.domain.aggregate.Recipe;
import com.ohgiraffers.recipeservice.recipe.command.domain.repository.RecipeRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaRecipeRepository extends RecipeRepository, JpaRepository<Recipe, Integer> {
}
