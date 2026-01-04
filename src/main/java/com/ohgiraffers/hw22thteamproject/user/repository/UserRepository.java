package com.ohgiraffers.hw22thteamproject.user.repository;

import com.ohgiraffers.hw22thteamproject.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // 로그인 아이디로 회원 찾기 (로그인 시 활용)
    Optional<User> findByLoginId(String loginId);
    
    // 이메일 중복 확인 등
    boolean existsByEmail(String email);
}