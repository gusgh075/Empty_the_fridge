package com.ohgiraffers.recipeservice.statistics.query.controller;

import java.util.List;
import java.util.Map;

import com.ohgiraffers.recipeservice.common.dto.ApiResponse;
import com.ohgiraffers.recipeservice.statistics.query.dto.request.DisposalCostRequest;
import com.ohgiraffers.recipeservice.statistics.query.dto.request.MonthlyPurchaseRequest;
import com.ohgiraffers.recipeservice.statistics.query.dto.response.CategoryPurchaseDTO;
import com.ohgiraffers.recipeservice.statistics.query.dto.response.DisposalCostResponse;
import com.ohgiraffers.recipeservice.statistics.query.dto.response.IngredientPurchaseDTO;
import com.ohgiraffers.recipeservice.statistics.query.dto.response.MonthlyDisposalDTO;
import com.ohgiraffers.recipeservice.statistics.query.service.StatisticsQueryService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class StatisticsQueryController {

	private final StatisticsQueryService statisticsQueryService;

	@GetMapping("/statistics/monthly")
	public ResponseEntity<ApiResponse<Map<String, Object>>> getMonthlyPurchaseByUser(
		@CookieValue(name = "refreshToken") String refreshToken,
		MonthlyPurchaseRequest monthlyPurchaseRequest
	) {
		Map<String, Object> response = statisticsQueryService.getMonthlyPurchaseDetails(refreshToken, monthlyPurchaseRequest);
		return ResponseEntity.ok(ApiResponse.success(response));
	}

	@GetMapping("/statistics/ingreient")
	public ResponseEntity<ApiResponse<List<IngredientPurchaseDTO>>> getIngredientPurchaseByUser(
		@CookieValue(name = "refreshToken") String refreshToken
	) {
		List<IngredientPurchaseDTO> result = statisticsQueryService.getIngredientPurchase(refreshToken);
		return ResponseEntity.ok(ApiResponse.success(result));
	}
	
	@GetMapping("/statistics/category")
	public ResponseEntity<ApiResponse<List<CategoryPurchaseDTO>>> getCategoryStats(
		@CookieValue(name = "refreshToken") String refreshToken
	) {
		List<CategoryPurchaseDTO> result = statisticsQueryService.getCategoryExpenseStats(refreshToken);

		return ResponseEntity.ok(ApiResponse.success(result));
	}

	@GetMapping("statistics/disposal")
	public ResponseEntity<ApiResponse<DisposalCostResponse>> getDisposalCostByUser(
		@CookieValue(name = "refreshToken") String refreshToken,
		DisposalCostRequest disposalCostRequest
	) {
		DisposalCostResponse result = statisticsQueryService.getDisposalCost(refreshToken, disposalCostRequest);

		return ResponseEntity.ok(ApiResponse.success(result));
	}

	@GetMapping("statistics/monthly-disposal")
	public ResponseEntity<ApiResponse<List<MonthlyDisposalDTO>>> getMonthlyDisposalByUser(
		@CookieValue(name = "refreshToken") String refreshToken
	) {
		List<MonthlyDisposalDTO> result = statisticsQueryService.getMonthlyDisposalList(refreshToken);

		return ResponseEntity.ok(ApiResponse.success(result));
	}

}