package com.ohgiraffers.userservice.user.command.domain.repository;




import com.ohgiraffers.userservice.user.command.domain.aggregate.User;

import java.util.Optional;

public interface UserRepository {

    User save(User user);

    Optional<User> findByUserId(String userId);

    boolean existsByUserId(String userId);

    boolean existsByNickname(String nickname);

    boolean existsByEmail(String email);

    boolean existsByPhoneNum(String phoneNum);

    User findByUserNo(long userNo);
}
