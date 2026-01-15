package com.ohgiraffers.userservice.user.command.infrastructure.repository;

import com.ohgiraffers.userservice.user.command.domain.aggregate.User;
import com.ohgiraffers.userservice.user.command.domain.repository.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<User, Long>, UserRepository {

}
