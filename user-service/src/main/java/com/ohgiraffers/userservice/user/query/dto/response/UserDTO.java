package com.ohgiraffers.userservice.user.query.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
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

}
