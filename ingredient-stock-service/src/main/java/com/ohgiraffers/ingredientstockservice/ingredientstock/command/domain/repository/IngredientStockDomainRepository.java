package com.ohgiraffers.ingredientstockservice.ingredientstock.command.domain.repository;

import com.ohgiraffers.ingredientstockservice.ingredientstock.command.domain.aggregate.IngredientStock;

import java.util.List;
import java.util.Optional;

public interface IngredientStockDomainRepository {

    IngredientStock save(IngredientStock ingredientStock);

    Optional<IngredientStock> findByUserNoAndIngredientStockNo(long userNo, long ingredientStockNo);

    List<IngredientStock> findAllByUserNo(long userNo);
}