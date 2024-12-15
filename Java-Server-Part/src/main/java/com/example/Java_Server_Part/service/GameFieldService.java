package com.example.Java_Server_Part.service;

import com.example.Java_Server_Part.model.Game;
import com.example.Java_Server_Part.model.GameField;
import com.example.Java_Server_Part.repository.GameFieldRepository;
import com.example.Java_Server_Part.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GameFieldService {

    private final GameFieldRepository gameFieldRepository;
    private final GameRepository gameRepository;

    public GameField getGameField(Long gameId) {
        try {
            return gameFieldRepository.findByGameId(gameId).get();
        } catch (Exception e) {
            log.warn("error!!!!!!!");
            return null;
        }
    }


    public void makeMove(Long gameId, int field, Long userId) {
        String step = "O";
        try {
            Game game = gameRepository.findByFirstUserId(userId).get();
            step = "X";
        } catch (Exception e) {
            log.warn("ход у нолика");
        }
        GameField gameField;
        try {
            gameField = gameFieldFindByGameIdLatest(gameId);
        } catch (Exception e) {
            gameField = gameFieldRepository.save(
                    new GameField(
                            gameFieldRepository.count(),
                            gameId,
                            " ",
                            " ",
                            " ",
                            " ",
                            " ",
                            " ",
                            " ",
                            " ",
                            " "
                    )
            );
        }

        if (gameField != null) {
            switch (field) {
                case 1:
                    gameField.setField1(step);
                    break;
                case 2:
                    gameField.setField2(step);
                    break;
                case 3:
                    gameField.setField3(step);
                    break;
                case 4:
                    gameField.setField4(step);
                    break;
                case 5:
                    gameField.setField5(step);
                    break;
                case 6:
                    gameField.setField6(step);
                    break;
                case 7:
                    gameField.setField7(step);
                    break;
                case 8:
                    gameField.setField8(step);
                    break;
                case 9:
                    gameField.setField9(step);
                    break;
                default:
                    log.warn("error!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            }
        }


    }

    private GameField gameFieldFindByGameIdLatest(Long gameId) {
        try {
            return gameFieldRepository.findTopByGameIdOrderByFieldIdDesc(gameId).get();
        } catch (Exception e) {
            log.warn("error!!!!!!!!!!!");
            return null;
        }
    }

    public boolean isValidMove(Long gameId, int field) {
        GameField gameField = gameFieldRepository.findByGameId(gameId).get();
        switch (field) {
            case 1:
                if (gameField.getField1().equals(" ")) {
                    return true;
                } else {
                    return false;
                }
            case 2:
                if (gameField.getField2().equals(" ")) {
                    return true;
                } else {
                    return false;
                }
            case 3:
                if (gameField.getField3().equals(" ")) {
                    return true;
                } else {
                    return false;
                }
            case 4:
                if (gameField.getField4().equals(" ")) {
                    return true;
                } else {
                    return false;
                }
            case 5:
                if (gameField.getField5().equals(" ")) {
                    return true;
                } else {
                    return false;
                }
            case 6:
                if (gameField.getField6().equals(" ")) {
                    return true;
                } else {
                    return false;
                }
            case 7:
                if (gameField.getField7().equals(" ")) {
                    return true;
                } else {
                    return false;
                }
            case 8:
                if (gameField.getField8().equals(" ")) {
                    return true;
                } else {
                    return false;
                }
            case 9:
                if (gameField.getField9().equals(" ")) {
                    return true;
                } else {
                    return false;
                }
            default:
                log.warn("error!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }


        throw new RuntimeException();
    }

    public boolean isWinner(Long gameId) {
        // Fetch the GameField record for the given gameId
        GameField gameField = gameFieldRepository.findByGameId(gameId)
                .orElseThrow(() -> new RuntimeException("No game field found for gameId " + gameId));

        // Check for horizontal wins
        if (checkLine(gameField.getField1(), gameField.getField2(), gameField.getField3())) return true;
        if (checkLine(gameField.getField4(), gameField.getField5(), gameField.getField6())) return true;
        if (checkLine(gameField.getField7(), gameField.getField8(), gameField.getField9())) return true;

        // Check for vertical wins
        if (checkLine(gameField.getField1(), gameField.getField4(), gameField.getField7())) return true;
        if (checkLine(gameField.getField2(), gameField.getField5(), gameField.getField8())) return true;
        if (checkLine(gameField.getField3(), gameField.getField6(), gameField.getField9())) return true;

        // Check for diagonal wins
        if (checkLine(gameField.getField1(), gameField.getField5(), gameField.getField9())) return true;
        if (checkLine(gameField.getField3(), gameField.getField5(), gameField.getField7())) return true;

        // No winner found
        return false;
    }

    // Helper method to check if three fields form a winning line
    private boolean checkLine(String field1, String field2, String field3) {
        return field1 != null && field1.equals(field2) && field1.equals(field3);
    }

}
