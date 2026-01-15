package com.ohgiraffers.mainservice.notification.command.infrastructure.repository;


import com.ohgiraffers.mainservice.notification.command.domain.aggregate.Notification;
import com.ohgiraffers.mainservice.notification.command.domain.repository.NotificationDomainRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaNotificationRepository extends JpaRepository<Notification, Long>, NotificationDomainRepository {

}
