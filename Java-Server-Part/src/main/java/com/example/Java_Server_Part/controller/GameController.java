//package com.example.Java_Server_Part.controller;
//
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.stereotype.Controller;
//
//@Controller
//public class GameController {
//
//    private GameState gameState = new GameState(); // Состояние игры, будет храниться на сервере
//
//    @MessageMapping("/move")
//    @SendTo("/topic/game")
//    public GameState makeMove(Move move) throws Exception {
//        // Проверка, что ход принадлежит текущему игроку
//        if (gameState.isGameOver()) {
//            throw new IllegalStateException("Game is already over");
//        }
//
//        if (!move.getPlayer().equals(gameState.getCurrentPlayer())) {
//            throw new IllegalStateException("It's not your turn");
//        }
//
//        // Проверка, что клетка свободна
//        if (!gameState.getBoard()[move.getX()][move.getY()].equals("")) {
//            throw new IllegalArgumentException("Cell is already taken");
//        }
//
//        // Применение хода
//        gameState.getBoard()[move.getX()][move.getY()] = move.getPlayer();
//
//        // Проверка на победу
//        checkWinner();
//
//        // Изменяем игрока на противоположного
//        if (!gameState.isGameOver()) {
//            gameState.setCurrentPlayer(gameState.getCurrentPlayer().equals("X") ? "O" : "X");
//        }
//
//        return gameState; // Отправляем обновленное состояние всем игрокам
//    }
//
//    private void checkWinner() {
//        // Проверка всех линий на победителя (по горизонтали, вертикали и диагоналям)
//        for (int i = 0; i < 3; i++) {
//            // Проверка горизонталей и вертикалей
//            if (gameState.getBoard()[i][0].equals(gameState.getBoard()[i][1]) &&
//                    gameState.getBoard()[i][1].equals(gameState.getBoard()[i][2]) &&
//                    !gameState.getBoard()[i][0].equals("")) {
//                gameState.setWinner(gameState.getBoard()[i][0]);
//                gameState.setGameOver(true);
//                return;
//            }
//
//            if (gameState.getBoard()[0][i].equals(gameState.getBoard()[1][i]) &&
//                    gameState.getBoard()[1][i].equals(gameState.getBoard()[2][i]) &&
//                    !gameState.getBoard()[0][i].equals("")) {
//                gameState.setWinner(gameState.getBoard()[0][i]);
//                gameState.setGameOver(true);
//                return;
//            }
//        }
//
//        // Проверка диагоналей
//        if (gameState.getBoard()[0][0].equals(gameState.getBoard()[1][1]) &&
//                gameState.getBoard()[1][1].equals(gameState.getBoard()[2][2]) &&
//                !gameState.getBoard()[0][0].equals("")) {
//            gameState.setWinner(gameState.getBoard()[0][0]);
//            gameState.setGameOver(true);
//            return;
//        }
//
//        if (gameState.getBoard()[0][2].equals(gameState.getBoard()[1][1]) &&
//                gameState.getBoard()[1][1].equals(gameState.getBoard()[2][0]) &&
//                !gameState.getBoard()[0][2].equals("")) {
//            gameState.setWinner(gameState.getBoard()[0][2]);
//            gameState.setGameOver(true);
//            return;
//        }
//
//        // Если все клетки заняты и нет победителя, то ничья
//        boolean isFull = true;
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                if (gameState.getBoard()[i][j].equals("")) {
//                    isFull = false;
//                    break;
//                }
//            }
//        }
//
//        if (isFull) {
//            gameState.setGameOver(true);
//        }
//    }
//}
//
