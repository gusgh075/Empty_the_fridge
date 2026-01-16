package com.ohgiraffers.recipeservice.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component // bean 등록
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    @Value("${jwt.refresh-expiration}")
    private long jwtRefreshExpiration;

    private SecretKey secretKey;

    @PostConstruct
    public void init() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        secretKey = Keys.hmacShaKeyFor(keyBytes);
    }

    /* access token 생성 메서드 */
    public String createToken(Long userNo, String userId, String role) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpiration);

        // JWT 토큰 생성 -> Header, Payload(Claims), Signature
        return Jwts.builder()
                .subject(userId)      // payload: subject (보통 사용자 식별) (등록 claim)
                .claim("role",role)  // payload: role (권한 정보) (공개 claim)
                .claim("userId", userNo.toString())
                .issuedAt(now)          // payload: issuedAt (발행 시간)
                .expiration(expiryDate) // payload: Expiration time (토큰 만료 시간)
                .signWith(secretKey)    // signature: 비밀키 서명 (위변조 방지)
                .compact();
    }

    /* refresh token 생성 메서드 */
    public String createRefreshToken(Long userNo, String userId, String role) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtRefreshExpiration);

        // JWT 토큰 생성 -> Header, Payload(Claims), Signature
        return Jwts.builder()
                .subject(userId)      // payload: subject (보통 사용자 식별) (등록 claim)
                .claim("role",role)  // payload: role (권한 정보) (공개 claim)
                .claim("userId", userNo.toString())
                .issuedAt(now)          // payload: issuedAt (발행 시간)
                .expiration(expiryDate) // payload: Expiration time (토큰 만료 시간)
                .signWith(secretKey)    // signature: 비밀키 서명 (위변조 방지)
                .compact();
    }

    // refresh token 만료 시간 반환 메서드
    public long getRefreshExpiration() {
        return jwtRefreshExpiration;
    }

    /* JWT Token 유효성 검사 메서드 */
    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            throw new BadCredentialsException("Invalid JWT Token", e);
        } catch (ExpiredJwtException e) {
            throw new BadCredentialsException("Expired JWT Token", e);
        } catch (UnsupportedJwtException e) {
            throw new BadCredentialsException("Unsupported JWT Token", e);
        } catch (IllegalArgumentException e) {
            throw new BadCredentialsException("JWT Token claims empty", e);
        }

    }

    /* JWT Token 중 payload의 claim 중에서 subject의 값을 반환하는 메서드 */
    public String getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims.getSubject();
    }

    public String getUserNoFromJWT(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims.get("userId", String.class);
    }

    public Date getIssuedAtDateFromJWT(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims.getIssuedAt();
    }

}
