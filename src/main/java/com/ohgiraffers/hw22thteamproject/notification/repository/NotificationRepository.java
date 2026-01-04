package com.ohgiraffers.hw22thteamproject.notification.repository;

import com.ohgiraffers.hw22thteamproject.notification.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    // 특정 사용자의 알림 설정 목록 조회
    List<Notification> findByUser_UserId(Integer userId);

    // 특정 사용자의 활성화된(Y) 알림만 조회
    List<Notification> findByUser_UserIdAndIsEnabled(Integer userId, String isEnabled);
}