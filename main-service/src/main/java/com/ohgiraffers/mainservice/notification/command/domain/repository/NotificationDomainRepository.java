package com.ohgiraffers.mainservice.notification.command.domain.repository;



import com.ohgiraffers.mainservice.notification.command.domain.aggregate.Notification;

import java.util.List;

public interface NotificationDomainRepository {
    <S extends Notification> List<S> saveAll(Iterable<S> entities);

    Notification getNotificationByNotificationNo(Long notificationNo);
}
