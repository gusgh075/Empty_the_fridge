package com.ohgiraffers.recipeservice.notification.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "notification")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Notification {

    @Id
    @Column(name = "notification_no", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long notificationNo;

    @Column(name = "user_no", nullable = false)
    private Long userNo;

    @Column(name = "notification_type_no", nullable = false)
    private long notificationTypeNo;

    @Column(name = "notification_content", nullable = false)
    private String notificationContent;

    @Column(name = "notification_is_checked", nullable = false)
    private boolean isChecked = false;

    @Column(name = "created_at", nullable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    @LastModifiedDate
    private LocalDateTime updatedAt;

    public static Notification createNotification(Long userNo, long notificationTypeNo, String notificationContent) {
        Notification notification = new Notification();
        notification.userNo = userNo;
        notification.notificationTypeNo = notificationTypeNo;
        notification.notificationContent = notificationContent;
        notification.isChecked = false;
        return notification;
    }

    public void setCheckTrue() {
        this.isChecked = true;
    }
}
