package com.ohgiraffers.ingredientstockservice.notification.command.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ohgiraffers.ingredientstockservice.notification.command.domain.aggregate.Notification;
import com.ohgiraffers.ingredientstockservice.notification.command.domain.repository.NotificationDomainRepository;

public interface JpaNotificationRepository extends JpaRepository<Notification, Long>, NotificationDomainRepository {

}
