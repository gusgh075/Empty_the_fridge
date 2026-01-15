package com.ohgiraffers.userservice.user.command.application.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserLoginRequest {

    private final String userId;
    private final String password;

}
