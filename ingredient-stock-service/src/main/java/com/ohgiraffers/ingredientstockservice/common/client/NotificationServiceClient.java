package com.ohgiraffers.ingredientstockservice.common.client;

import com.ohgiraffers.ingredientstockservice.common.dto.NotificationCreateRequest;
import com.ohgiraffers.ingredientstockservice.config.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "main-service",url = "http://localhost:8000", configuration = FeignClientConfig.class)
public interface NotificationServiceClient {

    @PostMapping("/api/v1/main-service/notifications")
    void createNotifications(@RequestBody List<NotificationCreateRequest> requests);
}
