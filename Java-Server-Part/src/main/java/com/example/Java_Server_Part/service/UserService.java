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
            return new UserModel();
        }
    }

    public void userSave(UserDto user) {
        try {
            if (user.getPassword().isBlank() || user.getUsername().isBlank()) {
                return;
            }
            userRepository.findByUsername(user.getUsername()).get();
        } catch (Exception e) {
            userRepository.save(new UserModel(
                    userRepository.count() + 1,
                    user.getUsername(),
                    user.getPassword(),
                    100,
                    "passive"
            ));
        }
    }

    public UserModel getUserById(String userId) {
        try {
            return userRepository.findById(Long.parseLong(userId)).get();
        } catch (NumberFormatException e) {
            log.warn("error!!!!!!!!!!!!!!!!");
            return new UserModel();
        }

    }

    public UserModel findOpponent() {
        try {
            return userRepository.findFirstUserByStatusSearch().get();
        } catch (Exception e) {
            log.warn("error !!!!!!!!!!!!!!!!!!");
            return null;
        }
    }

    public void updateUserStatus(UserModel user, String playing) {
        user.setStatus(playing);
    }

    public UserModel findById(Long userId) {
        try {
            return userRepository.findById(userId).get();
        } catch (Exception e) {
            log.warn("error!!!!!!!!!!");
            return null;
        }
    }
}
