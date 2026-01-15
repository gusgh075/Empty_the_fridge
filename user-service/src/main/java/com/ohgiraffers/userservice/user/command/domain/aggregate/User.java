package com.ohgiraffers.userservice.user.command.domain.aggregate;

import com.ohgiraffers.userservice.config.Constants;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA만 기본 생성자 사용 가능
@Getter
@EntityListeners(AuditingEntityListener.class) // Entity 삽입, 수정 감지 시 시간 기록
/* JPA의 delete 명령이 실행될 때 실제로 수행할 Native SQL을 지정. (Soft Delete 구현) */
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userNo;

    @Column(name = "user_id",unique = true, nullable = false)
    private String userId;

    @Column(name = "user_pwd", nullable = false)
    private String password;

    @Column(name = "user_nickname",unique = true, nullable = false)
    private String nickname;

    @Column(name = "user_email",unique = true, nullable = false)
    private String email;

    @Column(name = "user_phonenum",unique = true, nullable = false)
    private String phoneNum;

    @Column(name = "user_birthdate", nullable = false)
    private Date birthdate;

    @Column(name = "user_registered_at", nullable = false)
    @CreatedDate
    private Date registeredAt;

    @Column(name = "user_is_notice_active", nullable = false)
    private String isNoticeActive = Constants.NOTICE_DEFAULT_VALUE;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.ACTIVE;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role = UserRole.USER;

    @Column(name = "created_at", nullable = false)
    @CreatedDate // Entity 생성 시간을 자동 기록
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    @LastModifiedDate // Entity 값 변경 시간을 자동으로 기록하는 어노테이션
    private LocalDateTime updatedAt;


    /* 암호화된 비밀번호를 세팅하는 메서드 */
    public void setEncodedPassword(String encodedPassword) {
        this.password = encodedPassword;
    }

    /* 회원 email, phoneNum update 메서드 */
    public void updateUser(String email, String phoneNum) {
        this.email = email;
        this.phoneNum = phoneNum;
    }

    public void inActiveUser() {
        this.status = UserStatus.INACTIVE;
    }
}
