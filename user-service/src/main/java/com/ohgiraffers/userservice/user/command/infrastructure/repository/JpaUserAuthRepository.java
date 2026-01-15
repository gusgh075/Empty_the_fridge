package com.ohgiraffers.userservice.user.command.infrastructure.repository;


import com.ohgiraffers.userservice.user.command.domain.aggregate.RefreshToken;
import com.ohgiraffers.userservice.user.command.domain.repository.UserAuthRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserAuthRepository extends JpaRepository<RefreshToken, String>, UserAuthRepository {

}
