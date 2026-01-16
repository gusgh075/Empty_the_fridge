package com.ohgiraffers.recipeservice.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long userNo;
    private String id;
    private String password;
    private String nickname;
    private String email;
    private String phoneNum;
    private String birthdate;
    private Date registeredAt;
    private String isNoticeActive;
    private String role;

}
