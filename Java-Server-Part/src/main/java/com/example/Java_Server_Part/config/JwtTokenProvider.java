package com.example.Java_Server_Part.config;

import io.jsonwebtoken.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private final String JWT_SECRET = "secret_key"; // Секретный ключ для подписи токенов
    private final long JWT_EXPIRATION = 604800000L; // Время жизни токена - 7 дней

    public String generateToken(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION))
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    //извлекаем токен из заголовка
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    // check token
    public boolean validateToken(String token) {
        try {
//            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token);
            Jwts.parser().setSigningKey(JWT_SECRET).build().parseSignedClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser().setSigningKey(JWT_SECRET).build().parseSignedClaims(token).getBody();
        String username = claims.getSubject();//извлечение ключегого поля элемента

        UserPrincipal userPrincipal = new UserPrincipal(username); // Замените на вашу модель пользователя

        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(userPrincipal, "", userPrincipal.getAuthorities());
        return authenticationToken;
    }
}

