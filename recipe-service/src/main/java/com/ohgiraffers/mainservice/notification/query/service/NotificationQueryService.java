package com.ohgiraffers.mainservice.notification.query.service;

import com.ohgiraffers.mainservice.jwt.JwtTokenProvider;
import com.ohgiraffers.mainservice.notification.query.dto.response.NotificationDTO;
import com.ohgiraffers.mainservice.notification.query.dto.response.NotificationResponse;
import com.ohgiraffers.mainservice.notification.query.mapper.NotificationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationQueryService {

    private final NotificationMapper notificationMapper;
    private final JwtTokenProvider jwtTokenProvider;

    public NotificationResponse getNotificationByUserNo(String refreshToken) {
        // 토큰 검증 및 UserNo 가져오기
        this.jwtTokenProvider.validateToken(refreshToken);
        long userNo = Long.parseLong(this.jwtTokenProvider.getUserNoFromJWT(refreshToken));

        // user_no가 일치하는 notification 전부 불러오기
        List<NotificationDTO> notifications = this.notificationMapper.getNotificationsByUserNo(userNo);

        return NotificationResponse.builder().notifications(notifications).build();
    }
}
