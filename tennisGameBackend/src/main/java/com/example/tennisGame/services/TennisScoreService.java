package com.example.tennisGame.services;

import org.springframework.stereotype.Service;

@Service
public class TennisScoreService {
    private static final int[] scoreTable = {0, 15, 30, 40};

    public enum GameState { ONGOING, DEUCE, ADV_A, ADV_B, WIN_A, WIN_B }

    public static class TennisScore {
        public int pointsA = 0;
        public int pointsB = 0;
        public GameState state = GameState.ONGOING;
    }

    public TennisScore process(String sequence) {
        TennisScore score = new TennisScore();
        for (char c : sequence.toCharArray()) {
            if (score.state == GameState.WIN_A || score.state == GameState.WIN_B) break;
            updateScore(score, c);
        }
        return score;
    }

    public void updateScore(TennisScore score, char player) {
        score.state = switch (score.state) {
            case ONGOING -> {
                if (player == 'A') score.pointsA++;
                else score.pointsB++;

                if (score.pointsA == 3 && score.pointsB == 3) yield GameState.DEUCE;
                else if (score.pointsA > 3 && score.pointsA - score.pointsB > 1) yield GameState.WIN_A;
                else if (score.pointsB > 3 && score.pointsB - score.pointsA > 1) yield GameState.WIN_B;
                else if (score.pointsA > 3 || score.pointsB > 3)
                    yield (score.pointsA > score.pointsB) ? GameState.ADV_A : GameState.ADV_B;
                else yield GameState.ONGOING;
            }

            case DEUCE -> (player == 'A') ? GameState.ADV_A : GameState.ADV_B;

            case ADV_A -> (player == 'A') ? GameState.WIN_A : GameState.DEUCE;

            case ADV_B -> (player == 'B') ? GameState.WIN_B : GameState.DEUCE;

            default -> score.state; // If already finished, retain current state
        };

    }

    public String getScoreString(TennisScore score) {
        switch (score.state) {
            case WIN_A: return "Player A wins the game";
            case WIN_B: return "Player B wins the game";
            case DEUCE: return "Deuce";
            case ADV_A: return "Advantage Player A";
            case ADV_B: return "Advantage Player B";
            default:
                return String.format("Player A : %d / Player B : %d",
                        scoreTable[Math.min(score.pointsA, 3)], scoreTable[Math.min(score.pointsB, 3)]);
        }
    }
}
