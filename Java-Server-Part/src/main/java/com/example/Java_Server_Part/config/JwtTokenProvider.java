package com.example.Java_Server_Part.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    private final String JWT_SECRET = "secret_key"; // Секретный ключ для подписи токенов
    private final long JWT_EXPIRATION = 604800000L; // Время жизни токена - 7 дней

    public String generateToken(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
//        Key key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes());
//        byte[] keyBytes = this.JWT_SECRET.getBytes(StandardCharsets.UTF_8);
        SecretKey secretKey = Jwts.SIG.HS256.key().build();

        return Jwts.builder()
                .subject(userPrincipal.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION))
//                .setSubject(userPrincipal.getUsername())
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION))
//                .signWith(key, SignatureAlgorithm.HS512)
                .signWith(secretKey)
                .compact();
    }

    //извлекаем токен из заголовка
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }
    // check token
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).build().parseSignedClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser().setSigningKey(JWT_SECRET).build().parseSignedClaims(token).getBody();
        String username = claims.getSubject();//извлечение ключегого поля элемента
        // поиск user and his password
        String encodePassword = passwordEncoder.encode("password");

        UserPrincipal userPrincipal = new UserPrincipal(username, encodePassword); // Замените на вашу модель пользователя

        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(userPrincipal, "", userPrincipal.getAuthorities());
        return authenticationToken;
    }
}

