package com.ohgiraffers.userservice.user.command.application.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserUpdateRequest {

    private final String email;
    private final String phoneNum;

}
