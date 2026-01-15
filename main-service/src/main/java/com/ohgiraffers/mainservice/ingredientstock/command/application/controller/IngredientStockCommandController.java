package com.ohgiraffers.mainservice.ingredientstock.command.application.controller;


import com.ohgiraffers.mainservice.ingredientstock.command.application.dto.request.IngredientStockCreateRequest;
import com.ohgiraffers.mainservice.ingredientstock.command.application.dto.response.IngredientStockCreateResponse;
import com.ohgiraffers.mainservice.ingredientstock.command.application.dto.response.IngredientStockUpdateResponse;
import com.ohgiraffers.mainservice.ingredientstock.command.application.service.IngredientStockCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class IngredientStockCommandController {

    private final IngredientStockCommandService ingredientStockCommandService;

    /* 로그인한 유저의 식재료 정보 등록 */
    @PostMapping("/ingredient-stock")
    public ResponseEntity<ApiResponse<IngredientStockCreateResponse>> registIngredientStock(
            @CookieValue(name = "refreshToken") String refreshToken,
            @RequestBody IngredientStockCreateRequest ingredientStockCreateRequest
    ) {
        IngredientStockCreateResponse response = this.ingredientStockCommandService.registIngredientStock(refreshToken,ingredientStockCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(response));
    }

    /* 로그인한 유저의 식재료 정보 수정 */
    @PatchMapping("/ingredient-stock")
    public ResponseEntity<ApiResponse<IngredientStockUpdateResponse>> updateIngredientStock(
            @CookieValue(name = "refreshToken") String refreshToken,
            @RequestBody IngredientStockUpdateRequest ingredientStockUpdateRequest
    ) {
        IngredientStockUpdateResponse response = this.ingredientStockCommandService.updateIngredientStock(refreshToken, ingredientStockUpdateRequest);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(response));
    }

    @PostMapping("/ingredient-stock/notification")
    public ResponseEntity<ApiResponse<Void>> setIngredientStockNotice(
            @CookieValue(name = "refreshToken") String refreshToken
    ) {
        this.ingredientStockCommandService.setIngredientStockNotice(refreshToken);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(null));
    }
}
