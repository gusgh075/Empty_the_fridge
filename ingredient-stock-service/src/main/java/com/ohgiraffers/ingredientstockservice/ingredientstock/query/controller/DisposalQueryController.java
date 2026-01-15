package com.ohgiraffers.ingredientstockservice.ingredientstock.query.controller;

import com.ohgiraffers.ingredientstockservice.common.dto.ApiResponse;
import com.ohgiraffers.ingredientstockservice.ingredientstock.query.dto.response.DisposalHistoryResponse;
import com.ohgiraffers.ingredientstockservice.ingredientstock.query.service.DisposalQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DisposalQueryController {

    private final DisposalQueryService disposalQueryService;

    @GetMapping("/disposals")
    public ResponseEntity<ApiResponse<DisposalHistoryResponse>> getDisposalHistoryByUser(
            @AuthenticationPrincipal String userNo
    ) {
        DisposalHistoryResponse response = this.disposalQueryService.getDisposalHistories(userNo);
        return ResponseEntity.ok(ApiResponse.success(response));
    }


}
