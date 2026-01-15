package com.ohgiraffers.mainservice.recipe.command.domain.repository;



import com.ohgiraffers.mainservice.recipe.command.domain.aggregate.Dish;
import com.ohgiraffers.mainservice.recipe.command.domain.aggregate.Recipe;

import java.util.List;
import java.util.Optional;

public interface RecipeRepository {

	Recipe save(Recipe Recipe);

	Optional<Recipe> findById(Integer id);

	void deleteById(Integer id);

	List<Recipe> findByDishNo(Dish dish);

	void deleteAll(Iterable<? extends Recipe> entities);

}
