package com.ohgiraffers.mainservice.recipe.command.domain.repository;

import com.ohgiraffers.mainservice.recipe.command.domain.aggregate.DishCategory;

import java.util.Optional;



public interface DishCategoryRepository {
	Optional<DishCategory> findById(Integer id);
}
