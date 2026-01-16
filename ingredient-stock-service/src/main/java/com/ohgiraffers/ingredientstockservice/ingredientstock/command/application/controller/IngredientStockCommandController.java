package com.ohgiraffers.ingredientstockservice.ingredientstock.command.application.controller;


import com.ohgiraffers.ingredientstockservice.common.dto.ApiResponse;
import com.ohgiraffers.ingredientstockservice.ingredientstock.command.application.dto.request.IngredientStockCreateRequest;
import com.ohgiraffers.ingredientstockservice.ingredientstock.command.application.dto.request.IngredientStockUpdateRequest;
import com.ohgiraffers.ingredientstockservice.ingredientstock.command.application.dto.response.IngredientStockCreateResponse;
import com.ohgiraffers.ingredientstockservice.ingredientstock.command.application.dto.response.IngredientStockUpdateResponse;
import com.ohgiraffers.ingredientstockservice.ingredientstock.command.application.service.IngredientStockCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "식재료 재고 관리", description = "식재료 재고 등록, 수정, 알림 설정 API")
@RestController
@RequiredArgsConstructor
public class IngredientStockCommandController {

    private final IngredientStockCommandService ingredientStockCommandService;

    @Operation(
            summary = "식재료 재고 등록",
            description = "유통기한, 수량, 가격과 함께 새로운 식재료를 사용자의 재고에 추가합니다"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "식재료 재고 등록 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "잘못된 입력 데이터"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "인증되지 않음")
    })
    @PostMapping("/ingredient-stock")
    public ResponseEntity<ApiResponse<IngredientStockCreateResponse>> registIngredientStock(
            @AuthenticationPrincipal String userNo,
            @RequestBody IngredientStockCreateRequest ingredientStockCreateRequest
    ) {
        IngredientStockCreateResponse response = this.ingredientStockCommandService.registIngredientStock(userNo, ingredientStockCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(response));
    }

    @Operation(
            summary = "식재료 재고 수량 수정",
            description = "재고에 있는 식재료의 사용량을 업데이트합니다"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "식재료 재고 수정 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "식재료 재고를 찾을 수 없음"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "인증되지 않음")
    })
    @PatchMapping("/ingredient-stock")
    public ResponseEntity<ApiResponse<IngredientStockUpdateResponse>> updateIngredientStock(
            @AuthenticationPrincipal String userNo,
            @RequestBody IngredientStockUpdateRequest ingredientStockUpdateRequest
    ) {
        IngredientStockUpdateResponse response = this.ingredientStockCommandService.updateIngredientStock(userNo, ingredientStockUpdateRequest);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(response));
    }

    @Operation(
            summary = "유통기한 알림 설정",
            description = "유통기한이 임박한 식재료에 대한 알림을 활성화합니다"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "알림 설정 완료"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "인증되지 않음")
    })
    @PostMapping("/ingredient-stock/notification")
    public ResponseEntity<ApiResponse<Void>> setIngredientStockNotification(
            @AuthenticationPrincipal String userNo
    ) {
        this.ingredientStockCommandService.setIngredientStockNotice(userNo);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(null));
    }
}
