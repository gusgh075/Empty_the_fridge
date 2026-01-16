package com.ohgiraffers.recipeservice.recipe.command.infrastructure.repository;



import com.ohgiraffers.recipeservice.recipe.command.domain.aggregate.Dish;
import com.ohgiraffers.recipeservice.recipe.command.domain.repository.DishRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaDishRepository extends DishRepository, JpaRepository<Dish, Integer> {

}
