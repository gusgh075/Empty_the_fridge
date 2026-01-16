package com.ohgiraffers.ingredientstockservice.ingredientstock.query.controller;

import com.ohgiraffers.ingredientstockservice.common.dto.ApiResponse;
import com.ohgiraffers.ingredientstockservice.ingredientstock.query.dto.response.IngredientStockResponse;
import com.ohgiraffers.ingredientstockservice.ingredientstock.query.service.IngredientStockQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "식재료 재고 조회", description = "식재료 재고 정보 조회 API")
@RestController
@RequiredArgsConstructor
public class IngredientStockQueryController {

    private final IngredientStockQueryService ingredientStockQueryService;

    @Operation(
            summary = "전체 식재료 재고 조회",
            description = "인증된 사용자의 모든 식재료 재고를 조회합니다"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "식재료 목록 조회 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "인증되지 않음")
    })
    @GetMapping("/ingredient-stocks")
    public ResponseEntity<ApiResponse<IngredientStockResponse>> getIngredientStockList(
            @AuthenticationPrincipal String userNo
    ) {
        IngredientStockResponse response = this.ingredientStockQueryService.getIngredientStockList(userNo);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

}
