package com.ohgiraffers.mainservice.recipe.command.infrastructure.repository;



import com.ohgiraffers.mainservice.recipe.command.domain.aggregate.Recipe;
import com.ohgiraffers.mainservice.recipe.command.domain.repository.RecipeRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaRecipeRepository extends RecipeRepository, JpaRepository<Recipe, Integer> {
}
