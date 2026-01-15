package com.ohgiraffers.userservice.user.command.application.controller;


import com.ohgiraffers.userservice.common.dto.ApiResponse;
import com.ohgiraffers.userservice.user.command.application.dto.request.UserCreateRequest;
import com.ohgiraffers.userservice.user.command.application.dto.request.UserPwdUpdateRequest;
import com.ohgiraffers.userservice.user.command.application.dto.request.UserUpdateRequest;
import com.ohgiraffers.userservice.user.command.application.service.UserCommandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserCommandController {

    private final UserCommandService userCommandService;

    /* 회원가입 */
    @PostMapping("/users")
    public ResponseEntity<ApiResponse<Void>> register(@Valid @RequestBody UserCreateRequest userCreateRequest) {
        // 서비스 호출
        this.userCommandService.registUser(userCreateRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(null));
    }

    /* 회원정보 수정 (email, phoneNum ) */
    @PatchMapping("/users")
    public ResponseEntity<ApiResponse<Void>> updateUserInfo(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody UserUpdateRequest userUpdateRequest
    ) {
        // 1. 서비스 호출
        this.userCommandService.updateUser(userDetails, userUpdateRequest);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(null));
    }

    /* 회원정보 수정 (password) */
    @PatchMapping("/users/password")
    public ResponseEntity<ApiResponse<Void>> updatePassword(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody UserPwdUpdateRequest userPwdUpdateRequest
            ) {
        // 1. 서비스 호출
        this.userCommandService.updateUserPassword(userDetails, userPwdUpdateRequest);
        // return ResponseEntity
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(null));
    }

    @DeleteMapping("/users")
    public ResponseEntity<ApiResponse<Void>> deleteUser(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        // 1. 서비스 호출
        this.userCommandService.deleteUser(userDetails);
        // return ResponseEntity
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(null));
    }

}
