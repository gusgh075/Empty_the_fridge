package com.ohgiraffers.ingredientstockservice.notification.query.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ohgiraffers.ingredientstockservice.jwt.JwtTokenProvider;
import com.ohgiraffers.ingredientstockservice.notification.query.dto.response.NotificationDTO;
import com.ohgiraffers.ingredientstockservice.notification.query.dto.response.NotificationResponse;
import com.ohgiraffers.ingredientstockservice.notification.query.mapper.NotificationMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationQueryService {

    private final NotificationMapper notificationMapper;
    private final JwtTokenProvider jwtTokenProvider;

    public NotificationResponse getNotificationByUserNo(Long userNo) {

        // user_no가 일치하는 notification 전부 불러오기
        List<NotificationDTO> notifications = this.notificationMapper.getNotificationsByUserNo(userNo);

        return NotificationResponse.builder().notifications(notifications).build();
    }
}
