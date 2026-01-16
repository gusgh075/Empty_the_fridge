package com.ohgiraffers.recipeservice.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

/*
 * Gateway에서 전달된 헤더 기반 인증 필터 (Order Service용)
 * Gateway에서 이미 JWT 검증을 마쳤으므로, 전달된 사용자 ID와 Role 정보를 신뢰하고 SecurityContext에 인증 정보를 설정함
 */
@Component
@Slf4j
public class HeaderAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        // API Gateway가 전달한 헤더 읽기
        String userId = request.getHeader("X-User-Id");
        String role = request.getHeader("X-User-Role");
        String userNo = request.getHeader("X-User-No");

        log.info("userId : {}", userId);
        log.info("role : {}", role);

        if (userId != null && role != null) {
            // 이미 Gateway에서 검증된 정보로 인증 객체 구성
            PreAuthenticatedAuthenticationToken authentication =
                    new PreAuthenticatedAuthenticationToken(
                            userNo, null,
                            List.of(new SimpleGrantedAuthority(role))
                    );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
}
