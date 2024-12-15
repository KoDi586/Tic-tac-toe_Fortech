package com.example.Java_Server_Part.service;

import com.example.Java_Server_Part.dto.UserDto;
import com.example.Java_Server_Part.model.UserModel;
import com.example.Java_Server_Part.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public UserModel findByUsername(String username) {
        try {
            return userRepository.findByUsername(username).get();
        } catch (Exception e) {
            log.warn("not found user");
            return new UserModel(22L, "22user", "22password");
        }
    }

    public void userSave(UserDto user) {
        try {
            userRepository.findByUsername(user.getUsername()).get();
        } catch (Exception e) {
            userRepository.save(new UserModel(
                    userRepository.count() + 1,
                    user.getUsername(),
                    user.getPassword()
            ));
        }

    }
}
