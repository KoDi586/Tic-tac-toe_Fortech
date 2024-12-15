package com.example.Java_Server_Part.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "game")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Game {

    @Id
    @Column(name = "game_id")
    private Long gameId;

    @Column(name = "first_user_id")
    private Long firstUserId;

    @Column(name = "second_user_id")
    private Long secondUserId;

    @Column(name = "user_id_who_stepped")
    private Long userIdWhoStepped;

    @Column(name = "status")
    private String status;

}
