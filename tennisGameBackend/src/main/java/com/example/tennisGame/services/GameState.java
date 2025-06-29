package com.example.tennisGame.services;

public enum GameState {
    ONGOING("Game is ongoing"),
    DEUCE("Deuce"),
    ADV_A("Advantage Player A"),
    ADV_B("Advantage Player B"),
    WIN_A("Player A wins the game"),
    WIN_B("Player B wins the game");

    private final String message;

    GameState(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
