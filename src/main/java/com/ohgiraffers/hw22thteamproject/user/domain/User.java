package com.ohgiraffers.hw22thteamproject.user.domain;

import com.ohgiraffers.hw22thteamproject.BaseTime;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    private Integer userId; // user_id

    private String loginId;
    private String pwd;
    private String email;
    private String phonenum;
    private LocalDate birthdate;
    private Integer weight;
    private LocalDateTime registeredAt; // registered_at
    private String nickname;
    private String sex;

    @Embedded
    private BaseTime baseTime = new BaseTime();
}