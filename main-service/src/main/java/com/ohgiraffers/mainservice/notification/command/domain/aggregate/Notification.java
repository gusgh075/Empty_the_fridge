package com.ohgiraffers.mainservice.notification.command.domain.aggregate;

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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_no", nullable = false)
    private User user;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "notification_type_no", nullable = false)
    private NotificationType notificationType;

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

    public static Notification createNotification(User user, NotificationType notificationType, String notificationContent) {
        Notification notification = new Notification();
        notification.user = user;
        notification.notificationType = notificationType;
        notification.notificationContent = notificationContent;
        notification.isChecked = false;
        return notification;
    }

    public void setCheckTrue() {
        this.isChecked = true;
    }
}
