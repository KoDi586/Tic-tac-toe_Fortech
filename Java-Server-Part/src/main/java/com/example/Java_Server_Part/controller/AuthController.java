package com.example.Java_Server_Part.controller;

import com.example.Java_Server_Part.config.JwtTokenProvider;
import com.example.Java_Server_Part.dto.AuthRequestDto;
import com.example.Java_Server_Part.dto.TokenAndUserIdDto;
import com.example.Java_Server_Part.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

//    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
//        this.authenticationManager = authenticationManager;
//        this.jwtTokenProvider = jwtTokenProvider;
//    }

    // Endpoint для логина
    @PostMapping("/login")
    public ResponseEntity<TokenAndUserIdDto> login(@RequestBody AuthRequestDto authRequest) {
        Authentication authentication1 = new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(
                authentication1
        );
        log.info("authentication: {}", authentication);

        // Генерация JWT токена
        String token = jwtTokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new TokenAndUserIdDto(token, 1L));
    }

    // Метод для регистрации нового пользователя
    @PostMapping("/register")
    public String register(@RequestBody UserDto user) {
        // Логика регистрации пользователя (сохранение в базу данных и т.д.)
        return "User registered";
    }



}

