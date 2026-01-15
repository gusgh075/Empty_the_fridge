package com.ohgiraffers.mainservice.ingredientstock.query.controller;

import com.ohgiraffers.mainservice.common.dto.ApiResponse;
import com.ohgiraffers.mainservice.ingredientstock.query.dto.response.DisposalHistoryResponse;
import com.ohgiraffers.mainservice.ingredientstock.query.service.DisposalQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class DisposalQueryController {

    private final DisposalQueryService disposalQueryService;

    @GetMapping("/disposals")
    public ResponseEntity<ApiResponse<DisposalHistoryResponse>> getDisposalHistoryByUser(
            @CookieValue(name = "refreshToken") String refreshToken
    ) {
        DisposalHistoryResponse response = this.disposalQueryService.getDisposalHistories(refreshToken);
        return ResponseEntity.ok(ApiResponse.success(response));
    }


}
