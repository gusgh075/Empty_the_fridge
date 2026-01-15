package com.ohgiraffers.userservice.user.command.domain.service;


import com.ohgiraffers.userservice.user.command.application.dto.request.UserCreateRequest;
import com.ohgiraffers.userservice.user.command.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDomainService {

    private final UserRepository userRepository;

    public void validateValue(UserCreateRequest userCreateRequest) {

        // 1. userID 중복 검증
        if (userRepository.existsByUserId(userCreateRequest.getUserId())) {
            throw new IllegalStateException("이미 사용 중인 아이디입니다");
        }

        // 2. userNickname 중복 검증
        if (userRepository.existsByNickname(userCreateRequest.getNickname())) {
            throw new IllegalStateException("이미 사용 중인 닉네임입니다");
        }

        // 3. userEmail 중복 검증
        if (userRepository.existsByEmail(userCreateRequest.getEmail())) {
            throw new IllegalStateException("이미 가입된 이메일입니다");
        }

        // 4. phoneNum 중복 검증
        if (userRepository.existsByPhoneNum(userCreateRequest.getPhoneNum())) {
            throw new IllegalStateException("이미 가입된 휴대폰 번호 입니다.");
        }

    }
}
