package com.ohgiraffers.ingredientstockservice.notification.command.domain.aggregate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "notification_type")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class NotificationType {

    @Id
    @Column(name = "notification_type_no", nullable = false)
    private Integer notificationTypeNo;

    @Column(name = "notification_type_name", nullable = false)
    private String notificationTypeName;
}
