package com.ohgiraffers.hw22thteamproject.user.command.domain.aggregate;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = " ")
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA만 기본 생성자 사용 가능
@Getter
@EntityListeners(AuditingEntityListener.class) // Entity 삽입, 수정 감지 시 시간 기록
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userNo;

    @Column(name = "user_id", nullable = false)
    private String id;

    @Column(name = "user_pwd", nullable = false)
    private String password;

    @Column(name = "user_nickname", nullable = false)
    private String nickname;

    @Column(name = "user_email", nullable = false)
    private String email;

    @Column(name = "user_phonenum", nullable = false)
    private String phoneNum;

    @Column(name = "user_birthdate", nullable = false)
    private Date birthdate;

    @Column(name = "user_registered_at", nullable = false)
    private Date registered_at;

    @Column(name = "is_notice_active", nullable = false)
    private String is_notice_active;

    @Column(name = "created_at", nullable = false)
    @CreatedDate // Entity 생성 시간을 자동 기록
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    @LastModifiedDate // Entity 값 변경 시간을 자동으로 기록하는 어노테이션
    private LocalDateTime updatedAt;



}
