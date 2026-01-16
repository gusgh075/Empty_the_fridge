package com.ohgiraffers.ingredientstockservice.notification.command.application.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ohgiraffers.ingredientstockservice.exception.BusinessException;
import com.ohgiraffers.ingredientstockservice.exception.ErrorCode;
import com.ohgiraffers.ingredientstockservice.notification.command.application.dto.request.NotificationCreateRequest;
import com.ohgiraffers.ingredientstockservice.notification.command.domain.aggregate.Notification;
import com.ohgiraffers.ingredientstockservice.notification.command.domain.aggregate.NotificationType;
import com.ohgiraffers.ingredientstockservice.notification.command.domain.repository.NotificationDomainRepository;
import com.ohgiraffers.ingredientstockservice.notification.command.domain.repository.NotificationTypeDomainRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationCommandService {

    private final NotificationDomainRepository notificationDomainRepository;
    private final NotificationTypeDomainRepository notificationTypeDomainRepository;

    @Transactional
    public void checkNotification(Long notificationNo) {
        Notification notice = this.notificationDomainRepository.getNotificationByNotificationNo(notificationNo);
        notice.setCheckTrue();
    }

    @Transactional
    public void createNotifications(List<NotificationCreateRequest> requests) {
        if (requests == null || requests.isEmpty()) {
            return;
        }

        List<Notification> notifications = new ArrayList<>();

        for (NotificationCreateRequest request : requests) {
            NotificationType notificationType = this.notificationTypeDomainRepository
                    .findByNotificationTypeNo(request.getNotificationTypeNo())
                    .orElseThrow(() -> new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR));

            Notification notification = Notification.createNotification(
                    request.getUserNo(),
                    notificationType.getNotificationTypeNo(),
                    request.getNotificationContent()
            );
            notifications.add(notification);
        }

        this.notificationDomainRepository.saveAll(notifications);
    }
}
