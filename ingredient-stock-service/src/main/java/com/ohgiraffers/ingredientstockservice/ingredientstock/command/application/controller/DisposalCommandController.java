package com.ohgiraffers.ingredientstockservice.ingredientstock.command.application.controller;


import com.ohgiraffers.ingredientstockservice.common.dto.ApiResponse;
import com.ohgiraffers.ingredientstockservice.ingredientstock.command.application.dto.request.DisposalCreateRequest;
import com.ohgiraffers.ingredientstockservice.ingredientstock.command.application.dto.response.DisposalCreateResponse;
import com.ohgiraffers.ingredientstockservice.ingredientstock.command.application.service.DisposalCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class DisposalCommandController {

    private final DisposalCommandService disposalCommandService;

    /* 폐기 이력 추가 */
    @PostMapping("/disposal")
    public ResponseEntity<ApiResponse<DisposalCreateResponse>> disposal(
            @AuthenticationPrincipal String userNo,
            @RequestBody DisposalCreateRequest disposalCreateRequest
    ){
        DisposalCreateResponse response = this.disposalCommandService.createDisposal(userNo, disposalCreateRequest);
        return ResponseEntity.ok(ApiResponse.success(response));
    }


}
