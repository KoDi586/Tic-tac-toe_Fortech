package com.example.Java_Server_Part.controller.geme;

import com.example.Java_Server_Part.model.Game;
import com.example.Java_Server_Part.model.GameField;
import com.example.Java_Server_Part.model.UserModel;
import com.example.Java_Server_Part.service.GameFieldService;
import com.example.Java_Server_Part.service.GameService;
import com.example.Java_Server_Part.service.UserService;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GameWebSocketHandler extends TextWebSocketHandler {

    @Autowired
    private UserService userService; // Сервис для работы с пользователями
    @Autowired
    private GameService gameService; // Сервис для работы с играми
    @Autowired
    private GameFieldService gameFieldService; // Сервис для работы с игровыми полями

    private Map<String, WebSocketSession> activeSessions = new HashMap<>(); // Хранение активных сессий

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String userId = getUserIdFromSession(session);
        UserModel user = userService.getUserById(userId);

        if (user.getStatus().equals("search")) {
            // Если игрок ищет оппонента, пытаемся найти пару
            UserModel opponent = userService.findOpponent();
            if (opponent != null) {
                // Создаем игру для двух игроков
                Game game = gameService.createGame(user, opponent);
//                gameService.updateGameField(game.getGameId());//////////////////////////////////

                // Обновляем статус обоих игроков на "playing"
                userService.updateUserStatus(user, "playing");
                userService.updateUserStatus(opponent, "playing");

                // Подключаем игроков к игре
                sendGameStart(game, user, opponent);
            } else {
                // Если второго игрока нет, ставим статус "search"
                userService.updateUserStatus(user, "search");
                sendMessage(session, "Ожидайте второго игрока...");
            }
        } else if (user.getStatus().equals("playing")) {
            // Если пользователь уже в игре, добавляем его к активным сессиям
            activeSessions.put(userId, session);
            sendMessage(session, "Вы уже в игре");
        }
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String userId = getUserIdFromSession(session);
        UserModel user = userService.getUserById(userId);

        if (user.getStatus().equals("playing")) {
            // Обработка хода пользователя
            Game game = gameService.getGameByUserId(Long.parseLong(userId));
            if (game == null || game.getStatus().equals("passive")) {
                sendMessage(session, "Игра завершена");
                return;
            }

            if (!gameService.isUsersTurn(game, user)) {
                sendMessage(session, "Не ваш ход");
                return;
            }

            // Парсинг хода
            int field = Integer.parseInt(message.getPayload());
            if (!gameFieldService.isValidMove(game.getGameId(), field)) {
                sendMessage(session, "Невозможно сделать ход");
                return;
            }

            // Выполнение хода
            gameFieldService.makeMove(game.getGameId(), field, user.getId());

            // Проверка на победу
            if (gameFieldService.isWinner(game.getGameId())) {
                gameService.endGame(game);
                sendMessage(session, "Вы победили!");
                sendMessageToOpponent(game, user, "Вы проиграли!");
                return;
            }

            // Переключение хода на следующего игрока
            gameService.switchTurn(game);

            // Отправляем обновленное игровое поле
            sendGameStateToPlayers(game);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String userId = getUserIdFromSession(session);
        UserModel user = userService.getUserById(userId);

        if (user.getStatus().equals("playing")) {
            // Обработка разрыва соединения, если игрок уже в игре
            Game game = gameService.getGameByUserId(Long.parseLong(userId));
            if (game != null) {
                gameService.endGame(game); // Закрытие игры
                sendMessageToOpponent(game, user, "Противник покинул игру");
            }
        }
        activeSessions.remove(userId);
    }

    private String getUserIdFromSession(WebSocketSession session) {
        return (String) session.getAttributes().get("user_id"); // Получаем ID пользователя из атрибутов сессии
    }

    private void sendGameStart(Game game, UserModel user1, UserModel user2) throws IOException {
        // Отправляем игрокам информацию о начале игры
        sendMessage(activeSessions.get(user1.getId().toString()), "Игра началась");
        sendMessage(activeSessions.get(user2.getId().toString()), "Игра началась");

        // Отправляем начальное состояние игрового поля
        sendGameStateToPlayers(game);
    }

    private void sendGameStateToPlayers(Game game) throws IOException {
        GameField gameField = gameFieldService.getGameField(game.getGameId());
        String gameState = gameField.toString(); // Преобразуем игровое поле в строку

        sendMessage(activeSessions.get(game.getFirstUserId().toString()), gameState);
        sendMessage(activeSessions.get(game.getSecondUserId().toString()), gameState);
    }

    private void sendMessage(WebSocketSession session, String message) throws IOException {
        session.sendMessage(new TextMessage(message));
    }

    private void sendMessageToOpponent(Game game, UserModel user, String message) throws IOException {
        UserModel opponent = (game.getFirstUserId().equals(user.getId())) ? userService.findById(game.getSecondUserId()) : userService.findById(game.getFirstUserId());
        sendMessage(activeSessions.get(opponent.getId().toString()), message);
    }
}

