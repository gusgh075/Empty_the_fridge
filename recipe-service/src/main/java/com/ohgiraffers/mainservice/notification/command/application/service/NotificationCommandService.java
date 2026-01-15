package com.ohgiraffers.mainservice.notification.command.application.service;

import com.ohgiraffers.mainservice.notification.command.domain.aggregate.Notification;
import com.ohgiraffers.mainservice.notification.command.domain.repository.NotificationDomainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class NotificationCommandService {

    private final NotificationDomainRepository notificationDomainRepository;

    @Transactional
    public void checkNotification(Long notificationNo) {
        Notification notice = this.notificationDomainRepository.getNotificationByNotificationNo(notificationNo);
        notice.setCheckTrue();
    }
}
