package com.ohgiraffers.hw22thteamproject.notification.domain;

import com.ohgiraffers.hw22thteamproject.BaseTime;
import com.ohgiraffers.hw22thteamproject.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "notification")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notification {
    @Id
    private Integer notificationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private NotificationType type;

    private String isEnabled; // Y/N

    @Embedded
    private BaseTime baseTime = new BaseTime();

    public enum NotificationType { 유통기한임박, 유통기한만료, 재고소진임박 }
}