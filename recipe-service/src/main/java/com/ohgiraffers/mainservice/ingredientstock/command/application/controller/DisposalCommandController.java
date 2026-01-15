package com.ohgiraffers.mainservice.ingredientstock.command.application.controller;


import com.ohgiraffers.mainservice.common.dto.ApiResponse;
import com.ohgiraffers.mainservice.ingredientstock.command.application.dto.request.DisposalCreateRequest;
import com.ohgiraffers.mainservice.ingredientstock.command.application.dto.response.DisposalCreateResponse;
import com.ohgiraffers.mainservice.ingredientstock.command.application.service.DisposalCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class DisposalCommandController {

    private final DisposalCommandService disposalCommandService;

    @PostMapping("/disposal")
    public ResponseEntity<ApiResponse<DisposalCreateResponse>> disposal(
            @CookieValue(name = "refreshToken") String refreshToken,
            @RequestBody DisposalCreateRequest disposalCreateRequest
    ){
        DisposalCreateResponse response = this.disposalCommandService.createDisposal(refreshToken, disposalCreateRequest);
        return ResponseEntity.ok(ApiResponse.success(response));
    }


}
