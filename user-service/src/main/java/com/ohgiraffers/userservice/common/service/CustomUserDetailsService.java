package com.ohgiraffers.userservice.common.service;



import com.ohgiraffers.userservice.user.command.domain.aggregate.User;
import com.ohgiraffers.userservice.user.command.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    /* UserDetails : Spring Security가 관리하는 사용자 정보 객체 */
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        // 1) DB에서 user_id 일치하는 회원 조회
        User user = this.userRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("회원을 찾을 수 없습니다"));

        // 2) UserDetails Interface를 구현한 객체를 만들어서 반환
        return new org.springframework.security.core.userdetails.User(
                user.getUserId(),
                user.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority(user.getRole().name()))
        );
    }

}
