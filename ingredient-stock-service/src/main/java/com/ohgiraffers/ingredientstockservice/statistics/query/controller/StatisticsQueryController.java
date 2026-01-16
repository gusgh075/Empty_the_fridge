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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@Tag(name = "Statistics", description = "APIs for ingredient purchase and disposal statistics")
@RestController
@RequiredArgsConstructor
public class StatisticsQueryController {

	private final StatisticsQueryService statisticsQueryService;

	@Operation(
			summary = "Get monthly purchase statistics",
			description = "Retrieves monthly purchase details for the specified period"
	)
	@ApiResponses({
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Monthly statistics retrieved"),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized")
	})
	@GetMapping("/statistics/monthly")
	public ResponseEntity<ApiResponse<Map<String, Object>>> getMonthlyPurchaseByUser(
		@AuthenticationPrincipal String userNo,
		MonthlyPurchaseRequest monthlyPurchaseRequest
	) {
		Map<String, Object> response = statisticsQueryService.getMonthlyPurchaseDetails(Long.valueOf(userNo), monthlyPurchaseRequest);
		return ResponseEntity.ok(ApiResponse.success(response));
	}

	@Operation(
			summary = "Get ingredient purchase statistics",
			description = "Retrieves purchase statistics grouped by ingredient"
	)
	@ApiResponses({
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Ingredient statistics retrieved"),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized")
	})
	@GetMapping("/statistics/ingreient")
	public ResponseEntity<ApiResponse<List<IngredientPurchaseDTO>>> getIngredientPurchaseByUser(
		@AuthenticationPrincipal String userNo
	) {
		List<IngredientPurchaseDTO> result = statisticsQueryService.getIngredientPurchase(Long.valueOf(userNo));
		return ResponseEntity.ok(ApiResponse.success(result));
	}

	@Operation(
			summary = "Get category expense statistics",
			description = "Retrieves expense statistics grouped by ingredient category"
	)
	@ApiResponses({
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Category statistics retrieved"),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized")
	})
	@GetMapping("/statistics/category")
	public ResponseEntity<ApiResponse<List<CategoryPurchaseDTO>>> getCategoryStats(
		@AuthenticationPrincipal String userNo
	) {
		List<CategoryPurchaseDTO> result = statisticsQueryService.getCategoryExpenseStats(Long.valueOf(userNo));
		return ResponseEntity.ok(ApiResponse.success(result));
	}

	@Operation(
			summary = "Get disposal cost statistics",
			description = "Retrieves the total cost of disposed ingredients for the specified period"
	)
	@ApiResponses({
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Disposal cost retrieved"),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized")
	})
	@GetMapping("statistics/disposal")
	public ResponseEntity<ApiResponse<DisposalCostResponse>> getDisposalCostByUser(
		@AuthenticationPrincipal String userNo,
		DisposalCostRequest disposalCostRequest
	) {
		DisposalCostResponse result = statisticsQueryService.getDisposalCost(Long.valueOf(userNo), disposalCostRequest);
		return ResponseEntity.ok(ApiResponse.success(result));
	}

	@Operation(
			summary = "Get monthly disposal statistics",
			description = "Retrieves disposal statistics grouped by month"
	)
	@ApiResponses({
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Monthly disposal statistics retrieved"),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized")
	})
	@GetMapping("statistics/monthly-disposal")
	public ResponseEntity<ApiResponse<List<MonthlyDisposalDTO>>> getMonthlyDisposalByUser(
		@AuthenticationPrincipal String userNo
	) {
		List<MonthlyDisposalDTO> result = statisticsQueryService.getMonthlyDisposalList(Long.valueOf(userNo));
		return ResponseEntity.ok(ApiResponse.success(result));
	}

}