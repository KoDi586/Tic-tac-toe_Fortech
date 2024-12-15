package com.example.Java_Server_Part.repository;

import com.example.Java_Server_Part.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, Long> {

    Optional<Game> findByFirstUserId(Long userId);
    Optional<Game> findBySecondUserId(Long userId);
}
