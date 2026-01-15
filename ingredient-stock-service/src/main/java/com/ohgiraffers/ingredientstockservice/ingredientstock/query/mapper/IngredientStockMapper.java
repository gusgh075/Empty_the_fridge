package com.ohgiraffers.ingredientstockservice.ingredientstock.query.mapper;

import com.ohgiraffers.ingredientstockservice.ingredientstock.command.domain.aggregate.IngredientStock;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IngredientStockMapper {

    List<IngredientStock> getStocksByUserNo(long userNo);
}
