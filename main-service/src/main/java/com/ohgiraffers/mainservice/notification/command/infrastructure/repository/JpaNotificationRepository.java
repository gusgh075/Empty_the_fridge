package com.ohgiraffers.recipeservice.notification.command.infrastructure.repository;


import com.ohgiraffers.recipeservice.notification.command.domain.aggregate.Notification;
import com.ohgiraffers.recipeservice.notification.command.domain.repository.NotificationDomainRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaNotificationRepository extends JpaRepository<Notification, Long>, NotificationDomainRepository {

}
