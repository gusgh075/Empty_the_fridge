package com.ohgiraffers.ingredientstockservice.notification.command.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ohgiraffers.ingredientstockservice.notification.command.domain.aggregate.NotificationType;
import com.ohgiraffers.ingredientstockservice.notification.command.domain.repository.NotificationTypeDomainRepository;

public interface JpaNotificationTypeRepository extends JpaRepository<NotificationType, Integer>, NotificationTypeDomainRepository {
}