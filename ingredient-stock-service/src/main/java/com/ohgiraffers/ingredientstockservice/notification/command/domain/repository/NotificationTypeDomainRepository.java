package com.ohgiraffers.ingredientstockservice.notification.command.domain.repository;

import java.util.Optional;

import com.ohgiraffers.ingredientstockservice.notification.command.domain.aggregate.NotificationType;

public interface NotificationTypeDomainRepository {

    Optional<NotificationType> findByNotificationTypeNo(Integer notificationTypeNo);
}