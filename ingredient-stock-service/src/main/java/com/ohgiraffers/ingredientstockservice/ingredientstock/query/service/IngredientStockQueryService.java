package com.ohgiraffers.ingredientstockservice.ingredientstock.query.service;

import com.ohgiraffers.ingredientstockservice.ingredientstock.command.domain.aggregate.IngredientStock;
import com.ohgiraffers.ingredientstockservice.ingredientstock.query.dto.response.IngredientStockResponse;
import com.ohgiraffers.ingredientstockservice.ingredientstock.query.mapper.IngredientStockMapper;
import com.ohgiraffers.ingredientstockservice.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IngredientStockQueryService {

    private final IngredientStockMapper ingredientStockMapper;

    public IngredientStockResponse getIngredientStockList(String providedUserNo) {
        long userNo = Long.parseLong(providedUserNo);

        // ingredient_stock table에서 user_no와 매핑되는 컬럼 가져오기
        List<IngredientStock> ingredientStocks = this.ingredientStockMapper.getStocksByUserNo(userNo);

        return new IngredientStockResponse(ingredientStocks);
    }

}
