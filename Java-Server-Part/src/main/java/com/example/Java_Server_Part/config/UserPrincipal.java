package com.example.Java_Server_Part.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

public class UserPrincipal implements UserDetails {
    private String username;
    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(String username) {
        this.username = username;
        this.authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")); // Пример роли
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return username;
    }

    // Реализация других методов из интерфейса UserDetails
}
