package com.example.Java_Server_Part.repository;

import com.example.Java_Server_Part.model.GameField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface GameFieldRepository extends JpaRepository<GameField, Long> {

    Optional<GameField> findByGameId(Long gameId);

    @Query(value = "SELECT * FROM public.game_field WHERE game_id = ?1 ORDER BY field_id DESC LIMIT 1", nativeQuery = true)
    Optional<GameField> findTopByGameIdOrderByFieldIdDesc(Long gameId);
}
