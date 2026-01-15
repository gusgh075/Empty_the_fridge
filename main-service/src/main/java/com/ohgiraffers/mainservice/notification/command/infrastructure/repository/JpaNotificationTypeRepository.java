package com.ohgiraffers.mainservice.notification.command.infrastructure.repository;

import com.ohgiraffers.mainservice.notification.command.domain.aggregate.NotificationType;
import com.ohgiraffers.mainservice.notification.command.domain.repository.NotificationTypeDomainRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaNotificationTypeRepository extends JpaRepository<NotificationType, Integer>, NotificationTypeDomainRepository {
}