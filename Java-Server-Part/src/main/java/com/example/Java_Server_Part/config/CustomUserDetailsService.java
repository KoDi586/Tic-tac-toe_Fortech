package com.example.Java_Server_Part.config;

import com.example.Java_Server_Part.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String currentPassword = userService.findByUsername(username).getPassword();
        String encodedPassword = passwordEncoder.encode(currentPassword);
        return new UserPrincipal(username, encodedPassword); // Убедитесь, что роли добавляются в UserPrincipal
    }
}

