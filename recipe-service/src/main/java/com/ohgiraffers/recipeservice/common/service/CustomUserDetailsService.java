package com.ohgiraffers.recipeservice.common.service;

import com.ohgiraffers.recipeservice.common.client.UserServiceClient;
import com.ohgiraffers.recipeservice.common.dto.ApiResponse;
import com.ohgiraffers.recipeservice.common.dto.UserDTO;
import com.ohgiraffers.recipeservice.common.dto.UserDetailResponse;
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

    private final UserServiceClient userServiceClient;

    /* UserDetails : Spring Security가 관리하는 사용자 정보 객체 */
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        // 1) Feign client를 통해 user-service에서 회원 조회
        ApiResponse<UserDetailResponse> response = this.userServiceClient.getUserByUserId(userId);

        if (response == null || !response.getSuccess() || response.getData() == null) {
            throw new UsernameNotFoundException("회원을 찾을 수 없습니다");
        }

        UserDTO user = response.getData().getUser();
        if (user == null) {
            throw new UsernameNotFoundException("회원을 찾을 수 없습니다");
        }

        System.out.println("Role check: " + user.getRole());
        String roleName = user.getRole();
        roleName = "ROLE_" + roleName;

        // 2) UserDetails Interface를 구현한 객체를 만들어서 반환
        return new org.springframework.security.core.userdetails.User(
                user.getId(),
                user.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority(roleName))
        );
    }

}
