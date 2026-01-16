package com.ohgiraffers.recipeservice.recipe.command.domain.repository;



import com.ohgiraffers.recipeservice.recipe.command.domain.aggregate.Recipe;

import java.util.List;
import java.util.Optional;

public interface RecipeRepository {

	Recipe save(Recipe Recipe);

	Optional<Recipe> findById(Integer id);

	void deleteById(Integer id);

	List<Recipe> findByDish_DishNo(Integer dish);

	void deleteAll(Iterable<? extends Recipe> entities);

}
