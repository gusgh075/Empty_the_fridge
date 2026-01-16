package com.ohgiraffers.ingredientstockservice.statistics.query.controller;

import java.util.List;
import java.util.Map;

import com.ohgiraffers.ingredientstockservice.common.dto.ApiResponse;
import com.ohgiraffers.ingredientstockservice.statistics.query.dto.request.DisposalCostRequest;
import com.ohgiraffers.ingredientstockservice.statistics.query.dto.request.MonthlyPurchaseRequest;
import com.ohgiraffers.ingredientstockservice.statistics.query.dto.response.CategoryPurchaseDTO;
import com.ohgiraffers.ingredientstockservice.statistics.query.dto.response.DisposalCostResponse;
import com.ohgiraffers.ingredientstockservice.statistics.query.dto.response.IngredientPurchaseDTO;
import com.ohgiraffers.ingredientstockservice.statistics.query.dto.response.MonthlyDisposalDTO;
import com.ohgiraffers.ingredientstockservice.statistics.query.service.StatisticsQueryService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class StatisticsQueryController {

	private final StatisticsQueryService statisticsQueryService;

	@GetMapping("/statistics/monthly")
	public ResponseEntity<ApiResponse<Map<String, Object>>> getMonthlyPurchaseByUser(
		@AuthenticationPrincipal String userNo,
		MonthlyPurchaseRequest monthlyPurchaseRequest
	) {
		Map<String, Object> response = statisticsQueryService.getMonthlyPurchaseDetails(Long.valueOf(userNo), monthlyPurchaseRequest);
		return ResponseEntity.ok(ApiResponse.success(response));
	}

	@GetMapping("/statistics/ingreient")
	public ResponseEntity<ApiResponse<List<IngredientPurchaseDTO>>> getIngredientPurchaseByUser(
		@AuthenticationPrincipal String userNo
	) {
		List<IngredientPurchaseDTO> result = statisticsQueryService.getIngredientPurchase(Long.valueOf(userNo));
		return ResponseEntity.ok(ApiResponse.success(result));
	}
	
	@GetMapping("/statistics/category")
	public ResponseEntity<ApiResponse<List<CategoryPurchaseDTO>>> getCategoryStats(
		@AuthenticationPrincipal String userNo
	) {
		List<CategoryPurchaseDTO> result = statisticsQueryService.getCategoryExpenseStats(Long.valueOf(userNo));

		return ResponseEntity.ok(ApiResponse.success(result));
	}

	@GetMapping("statistics/disposal")
	public ResponseEntity<ApiResponse<DisposalCostResponse>> getDisposalCostByUser(
		@AuthenticationPrincipal String userNo,
		DisposalCostRequest disposalCostRequest
	) {
		DisposalCostResponse result = statisticsQueryService.getDisposalCost(Long.valueOf(userNo), disposalCostRequest);

		return ResponseEntity.ok(ApiResponse.success(result));
	}

	@GetMapping("statistics/monthly-disposal")
	public ResponseEntity<ApiResponse<List<MonthlyDisposalDTO>>> getMonthlyDisposalByUser(
		@AuthenticationPrincipal String userNo
	) {
		List<MonthlyDisposalDTO> result = statisticsQueryService.getMonthlyDisposalList(Long.valueOf(userNo));

		return ResponseEntity.ok(ApiResponse.success(result));
	}

}