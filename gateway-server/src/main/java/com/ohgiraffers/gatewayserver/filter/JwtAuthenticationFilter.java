package com.ohgiraffers.gatewayserver.filter;

import com.ohgiraffers.gateway.jwt.GatewayJwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter implements GlobalFilter, Ordered {

    private final GatewayJwtTokenProvider jwtTokenProvider;

    /**
     * 필터링 로직의 핵심 메서드
     * ServerWebExchange: 요청과 응답을 모두 담고 있는 컨텍스트 객체 (HttpServletRequest/Response의 Reactive 버전)
     * GatewayFilterChain: 다음 필터로 넘겨주는 체인 객체
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1. 요청 헤더에서 "Authorization" 값을 읽어옴
        String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");

        // 2. 만약 토큰이 없거나, "Bearer "로 시작하지 않으면 다음 체인으로 요청 (Pass-through)
        //    (로그인하지 않은 사용자도 접근 가능한 페이지가 있을 수 있기 때문)
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return chain.filter(exchange);
        }

        // 3. "Bearer " 접두어를 제거하고 순수 JWT 토큰만 추출한다.
        String token = authHeader.substring(7);

        // 4. JWT 토큰의 유효성을 확인한다. (위조되었거나 만료되었는지 확인)
        if (!jwtTokenProvider.validateToken(token)) {
            // 유효하지 않다면 401(Unauthorized) 상태 코드를 응답하고 연결을 끊음
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        // 5. 토큰이 유효하다면, 토큰 안에 있는 사용자 정보(ID, Role)를 꺼내옴
        Long userId = jwtTokenProvider.getUserIdFromJWT(token);
        String role = jwtTokenProvider.getRoleFromJWT(token);

        // 6. [중요] 백엔드 마이크로서비스(first-service 등)가 사용할 수 있도록 
        //    사용자 정보를 헤더('X-User-Id', 'X-User-Role')에 담아서 전달함.
        //    (WebFlux에서 요청 객체는 불변(Immutable)이므로 mutate()를 사용해 복제본을 만든다)
        ServerHttpRequest mutateRequest = exchange.getRequest().mutate()
                .header("X-User-Id", String.valueOf(userId))
                .header("X-User-Role", role)
                .build();

        // 7. 변경 된 요청 객체를 포함하는 새로운 ServerWebExchange를 생성하여 다음 필터로 전달함
        ServerWebExchange mutatedExchange = exchange.mutate().request(mutateRequest).build();

        return chain.filter(mutatedExchange);
    }

    /**
     * 필터의 실행 순서를 결정을 위한 메서드
     * 숫자가 작을 수록 높은 우선 순위를 갖는다 (-1이면 매우 빨리 실행됨)
     */
    @Override
    public int getOrder() {
        return -1;
    }
}