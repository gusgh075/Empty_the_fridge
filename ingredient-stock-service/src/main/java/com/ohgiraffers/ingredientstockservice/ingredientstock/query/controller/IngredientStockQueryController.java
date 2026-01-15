package com.ohgiraffers.ingredientstockservice.ingredientstock.query.controller;

import com.ohgiraffers.ingredientstockservice.common.dto.ApiResponse;
import com.ohgiraffers.ingredientstockservice.ingredientstock.query.dto.response.IngredientStockResponse;
import com.ohgiraffers.ingredientstockservice.ingredientstock.query.service.IngredientStockQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class IngredientStockQueryController {

    private final IngredientStockQueryService ingredientStockQueryService;

    /* UserNo에 맞는 Ingredient stock 전부 조회 */
    @GetMapping("/ingredient-stocks")
    public ResponseEntity<ApiResponse<IngredientStockResponse>> getIngredientStockList(
            @AuthenticationPrincipal String userNo
    ){
        IngredientStockResponse response = this.ingredientStockQueryService.getIngredientStockList(userNo);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

}
