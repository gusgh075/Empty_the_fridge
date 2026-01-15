package com.ohgiraffers.gatewayserver.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class GatewayJwtTokenProvider {

    // application.yml의 jwt.secret 값을 가져옴
    @Value("${jwt.secret}")
    private String jwtSecret;

    private SecretKey secretKey;

    // 빈 생성 후 딱 한 번 실행되어 비밀키(SecretKey) 객체를 세팅함
    @PostConstruct
    public void init() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        secretKey = Keys.hmacShaKeyFor(keyBytes); // HMAC-SHA 알고리즘용 키 생성
    }

    // 토큰이 위조되지 않았는지 확인
    public boolean validateToken(String token) {
        try {
            // 비밀키로 서명을 풀어서 확인, 실패 시 예외 발생됨
            Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            // 파싱 실패 (위조, 만료 등) -> 유효하지 않은 토큰으로 간주
            throw new RuntimeException("Invalid JWT Token", e);
        }
    }

    // 토큰 payload(내용물)에서 userId 꺼내오기
    public Long getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims.get("userId", Long.class);
    }

    // 토큰 payload(내용물)에서 role(권한) 꺼내오기
    public String getRoleFromJWT(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims.get("role", String.class);
    }

}