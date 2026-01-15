package com.ohgiraffers.userservice.user.query.controller;


import com.ohgiraffers.userservice.common.dto.ApiResponse;
import com.ohgiraffers.userservice.user.query.dto.response.UserDetailResponse;
import com.ohgiraffers.userservice.user.query.service.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserQueryController {

    private final UserQueryService userQueryService;

    @GetMapping("/users/{user_id}")
    public ResponseEntity<ApiResponse<UserDetailResponse>> getUser(@PathVariable("user_id") String userId) {
        UserDetailResponse response = this.userQueryService.getUser(userId);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @GetMapping("/users/myinfo")
    public ResponseEntity<ApiResponse<UserDetailResponse>> getMyInfo(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        UserDetailResponse response = this.userQueryService.getUser(userDetails.getUsername());
        return ResponseEntity.ok(ApiResponse.success(response));
    }


}
