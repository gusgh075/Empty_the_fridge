package com.ohgiraffers.mainservice.recipe.command.infrastructure.repository;

import com.ohgiraffers.mainservice.recipe.command.domain.aggregate.DishCategory;
import com.ohgiraffers.mainservice.recipe.command.domain.repository.DishCategoryRepository;
import org.springframework.data.jpa.repository.JpaRepository;


public interface JpaDishCategoryRepository extends DishCategoryRepository, JpaRepository<DishCategory, Integer> {
}
