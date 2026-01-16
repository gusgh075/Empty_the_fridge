package com.ohgiraffers.ingredientstockservice.ingredientstock.command.application.controller;


import com.ohgiraffers.ingredientstockservice.common.dto.ApiResponse;
import com.ohgiraffers.ingredientstockservice.ingredientstock.command.application.dto.request.DisposalCreateRequest;
import com.ohgiraffers.ingredientstockservice.ingredientstock.command.application.dto.response.DisposalCreateResponse;
import com.ohgiraffers.ingredientstockservice.ingredientstock.command.application.service.DisposalCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@Tag(name = "폐기 관리", description = "식재료 폐기 기록 API")
@RestController
@RequiredArgsConstructor
public class DisposalCommandController {

    private final DisposalCommandService disposalCommandService;

    @Operation(
            summary = "폐기 기록",
            description = "식재료의 폐기 수량과 사유를 기록합니다"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "폐기 기록 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "잘못된 폐기 데이터"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "식재료 재고를 찾을 수 없음"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "인증되지 않음")
    })
    @PostMapping("/disposal")
    public ResponseEntity<ApiResponse<DisposalCreateResponse>> disposal(
            @AuthenticationPrincipal String userNo,
            @RequestBody DisposalCreateRequest disposalCreateRequest
    ) {
        DisposalCreateResponse response = this.disposalCommandService.createDisposal(userNo, disposalCreateRequest);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

}
