package com.example.tennisGame.controllers;

import com.example.tennisGame.services.GameState;
import com.example.tennisGame.services.TennisScoreService;
import com.example.tennisGame.services.TennisScoreService.TennisScore;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tennis")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class TennisController {

    public static final String URI_PLAY = "/play";
    public static final String TOPIC_TENNIS_SCORE = "tennis-score";
    private final  TennisScoreService service;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping(URI_PLAY)
    public List<String> play(@RequestBody String sequence) {
        List<String> results = new ArrayList<>();
        TennisScore score = new TennisScore();
        for (char c : sequence.trim().toUpperCase().toCharArray()) {
            if (score.state == GameState.WIN_A || score.state == GameState.WIN_B)
                break;
            service.updateScore(score, c);
            String s = service.getScoreString(score);
            results.add(s);
            kafkaTemplate.send(TOPIC_TENNIS_SCORE, s); // envoie le score à Kafka
        }
        return results;
    }
}