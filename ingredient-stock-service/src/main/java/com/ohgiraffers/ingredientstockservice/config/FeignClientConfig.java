package com.ohgiraffers.ingredientstockservice.config;

import feign.RequestInterceptor;
import org.apache.http.HttpHeaders;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/*
 * Feign Client 설정 클래스
 * Feign Client가 요청을 보낼 때 헤더를 조작하기 위한 인터셉터를 등록함
 */
@Configuration
public class FeignClientConfig {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {

            /* 현재 요청의 Http Servlet Request 를 가져옴 */
            ServletRequestAttributes requestAttributes =
                    (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

            if (requestAttributes != null) {

                // 1. Gateway를 요청하는 상황 (Gateway가 토큰 검증을 수행하므로 토큰을 그대로 전달)
                /* 현재 요청의 Authorization 헤더 추출 (Bearer 토큰) */
                String authorizationHeader = requestAttributes
                        .getRequest()
                        .getHeader(HttpHeaders.AUTHORIZATION);


                if (authorizationHeader != null) {       // 토큰을 들고 왔다면

//                     Feign client 요청에 "Authorization" 헤더 추가
                    requestTemplate.header(HttpHeaders.AUTHORIZATION, authorizationHeader);

                }

                // 2. 내부 user service를 요청하는 상황 (Gateway를 거치지 않고 직접 호출 시)
                // Gateway가 넣어준 X-User-Id, X-User-Role 헤더를 그대로 전달해야 할 수도 있음
//                String userId = requestAttributes.getRequest().getHeader("X-User-Id");
//                String role = requestAttributes.getRequest().getHeader("X-User-Role");
//                requestTemplate.header("X-User-Id", userId);
//                requestTemplate.header("X-User-Role", role);
            }
        };
    }
}
