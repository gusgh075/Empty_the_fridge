package com.ohgiraffers.recipeservice.common.client;

import com.ohgiraffers.recipeservice.common.dto.ApiResponse;
import com.ohgiraffers.recipeservice.common.dto.UserDetailResponse;
import com.ohgiraffers.recipeservice.config.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "http://localhost:8000", configuration = FeignClientConfig.class)
public interface UserServiceClient {

    @GetMapping("/api/v1/user-service//users/{user_no}")
    ApiResponse<UserDetailResponse> getUserByUserNo(@PathVariable("userNo") Long userNo);

    @GetMapping("/api/v1/user-service/users/{userId}")
    ApiResponse<UserDetailResponse> getUserByUserId(@PathVariable("userId") String userId);

}
