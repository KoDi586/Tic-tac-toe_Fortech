package com.example.Java_Server_Part.controller;

import com.example.Java_Server_Part.config.JwtTokenProvider;
import com.example.Java_Server_Part.dto.AuthRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public String login(@RequestBody AuthRequestDto authRequest) {
        log.info("login data = {}", authRequest);
        Authentication authentication1 = new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword());
        log.info("authentication1: {}", authentication1);
        Authentication authentication = authenticationManager.authenticate(
                authentication1
        );
        log.info("authentication: {}", authentication);

        // Генерация JWT токена
        String token = jwtTokenProvider.generateToken(authentication);
        return token;
    }

    // Метод для регистрации нового пользователя
//    @PostMapping("/register")
//    public String register(@RequestBody UserDto user) {
//        // Логика регистрации пользователя (сохранение в базу данных и т.д.)
//        return "User registered";
//    }
    @PostMapping("/register")
    public String register(@RequestParam Long test) {
        // Логика регистрации пользователя (сохранение в базу данных и т.д.)
        return "User registered";
    }

    @GetMapping("/login")
    public String getTest() {
        return "working";
    }
}

