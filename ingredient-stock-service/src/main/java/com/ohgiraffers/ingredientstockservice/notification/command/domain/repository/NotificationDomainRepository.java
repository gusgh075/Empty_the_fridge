package com.ohgiraffers.ingredientstockservice.notification.command.domain.repository;

import java.util.List;

import com.ohgiraffers.ingredientstockservice.notification.command.domain.aggregate.Notification;

public interface NotificationDomainRepository {
    <S extends Notification> List<S> saveAll(Iterable<S> entities);

    Notification getNotificationByNotificationNo(Long notificationNo);
}
