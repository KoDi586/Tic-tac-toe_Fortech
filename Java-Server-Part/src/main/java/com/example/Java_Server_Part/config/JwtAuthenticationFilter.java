package com.example.Java_Server_Part.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import jakarta.servlet.FilterChain;
import javax.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    // Конструктор для инъекции JwtTokenProvider
//    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
//        this.jwtTokenProvider = jwtTokenProvider;
//    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getRequestURI().startsWith("/login") || request.getRequestURI().startsWith("/register")) {
            filterChain.doFilter(request, response);  // Пропускаем фильтр для этих маршрутов
            return;
        }
        // Извлекаем токен из заголовков запроса
        String token = jwtTokenProvider.resolveToken(request);

        // Если токен валиден, аутентифицируем пользователя
        if (token != null && jwtTokenProvider.validateToken(token)) {
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            // Устанавливаем аутентификацию в SecurityContext
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // Продолжаем выполнение цепочки фильтров
        filterChain.doFilter(request, response);
    }
}

