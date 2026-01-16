package com.ohgiraffers.recipeservice.recipe.command.domain.repository;



import java.util.Optional;

import com.ohgiraffers.recipeservice.recipe.command.domain.aggregate.Dish;

public interface DishRepository {

	Dish save(Dish dish);

	Optional<Dish> findById(Integer id);

	void deleteById(Integer id);

	Optional<Dish> findByDishName(String dishName);

}
