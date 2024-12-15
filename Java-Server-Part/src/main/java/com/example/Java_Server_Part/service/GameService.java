package com.example.Java_Server_Part.service;

import com.example.Java_Server_Part.model.Game;
import com.example.Java_Server_Part.model.UserModel;
import com.example.Java_Server_Part.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameService {
    private final GameRepository gameRepository;

    public Game createGame(UserModel user, UserModel opponent) {
        Game game = new Game();
        game.setFirstUserId(user.getId());
        game.setSecondUserId(opponent.getId());
        game.setStatus("active");
        game.setUserIdWhoStepped(opponent.getId());
        game.setGameId(gameRepository.count()+1);
        return gameRepository.save(game);
    }

    public Game getGameByUserId(Long userId) {
        try {
            return gameRepository.findByFirstUserId(userId).get();
        } catch (Exception e) {
            return gameRepository.findBySecondUserId(userId).get();
        }
    }

    public void endGame(Game game) {
        game.setStatus("finish");
        gameRepository.save(game);
    }

    public void switchTurn(Game game) {
        if (game.getUserIdWhoStepped().equals(game.getFirstUserId())) {
            game.setUserIdWhoStepped(game.getSecondUserId());
        } else {
            game.setUserIdWhoStepped(game.getFirstUserId());
        }
    }

    public boolean isUsersTurn(Game game, UserModel user) {
        return ! (game.getUserIdWhoStepped().equals(user.getId()));
    }
}
