package com.ohgiraffers.ingredientstockservice.ingredientstock.query.controller;

import com.ohgiraffers.ingredientstockservice.common.dto.ApiResponse;
import com.ohgiraffers.ingredientstockservice.ingredientstock.query.dto.response.DisposalHistoryResponse;
import com.ohgiraffers.ingredientstockservice.ingredientstock.query.service.DisposalQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "폐기 내역 조회", description = "식재료 폐기 이력 조회 API")
@RestController
@RequiredArgsConstructor
public class DisposalQueryController {

    private final DisposalQueryService disposalQueryService;

    @Operation(
            summary = "폐기 이력 조회",
            description = "인증된 사용자의 모든 폐기 기록을 조회합니다"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "폐기 이력 조회 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "인증되지 않음")
    })
    @GetMapping("/disposals")
    public ResponseEntity<ApiResponse<DisposalHistoryResponse>> getDisposalHistoryByUser(
            @AuthenticationPrincipal String userNo
    ) {
        DisposalHistoryResponse response = this.disposalQueryService.getDisposalHistories(userNo);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

}
