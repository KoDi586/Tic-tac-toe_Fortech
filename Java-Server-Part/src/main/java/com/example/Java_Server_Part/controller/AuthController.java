package com.example.Java_Server_Part.controller;

import com.example.Java_Server_Part.config.JwtTokenProvider;
import com.example.Java_Server_Part.dto.AuthRequestDto;
import com.example.Java_Server_Part.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

//    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
//        this.authenticationManager = authenticationManager;
//        this.jwtTokenProvider = jwtTokenProvider;
//    }

    // Endpoint для логина
    @PostMapping("/login")
    public String login(@RequestBody AuthRequestDto authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );

        // Генерация JWT токена
        String token = jwtTokenProvider.generateToken(authentication);
        return token;
    }

    // Метод для регистрации нового пользователя
    @PostMapping("/register")
    public String register(@RequestBody UserDto user) {
        // Логика регистрации пользователя (сохранение в базу данных и т.д.)
        return "User registered";
    }

    @GetMapping("/login")
    public String getTest() {
        return "working";
    }
}

