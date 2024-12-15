package com.example.Java_Server_Part.config;

import com.example.Java_Server_Part.service.UserService;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;

import javax.crypto.SecretKey;

import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {
//    private final String JWT_SECRET = "secret_key"; // Секретный ключ для подписи токенов
    private final long JWT_EXPIRATION = 604800000L; // Время жизни токена - 7 дней
    private final SecretKey secretKey = Jwts.SIG.HS256.key().build();
    private final UserService userService;

    public String generateToken(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        return Jwts.builder()
                .subject(userPrincipal.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION))
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
            Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        String username = claims.getSubject();//извлечение ключегого поля элемента
        // поиск user and his password
        String currentPassword = userService.findByUsername(username).getPassword();

        String encodePassword = passwordEncoder.encode(currentPassword);

        UserPrincipal userPrincipal = new UserPrincipal(username, encodePassword); // Замените на вашу модель пользователя

        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(userPrincipal, "", userPrincipal.getAuthorities());
        return authenticationToken;
    }
}

