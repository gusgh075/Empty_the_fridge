package com.ohgiraffers.userservice.user.command.domain.repository;


import com.ohgiraffers.userservice.user.command.domain.aggregate.RefreshToken;

public interface UserAuthRepository {

    RefreshToken save(RefreshToken tokenEntity);

    void deleteById(String userId);
}
