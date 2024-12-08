package com.example.kata.service;

import com.example.kata.model.GameScore;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.IllegalCharsetNameException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class GameService {

    @Value("${application.game.scores}")
    private final List<String> scores;

    public List<String> playGame(String input) {
        List<String> result = new ArrayList<>();
        GameScore score = new GameScore(scores.get(0), scores.get(0));
        String roundResult;
        for (char c : input.toCharArray()) {
            roundResult = incrementScores(score, c);
            log.info(roundResult);
            result.add(roundResult);
        }
        return result;
    }

    private String incrementScores(GameScore score, char c) {
        int indexA = scores.indexOf(score.getScorePlayerA());
        int indexB = scores.indexOf(score.getScorePlayerB());
        return switch (c) {
            case 'A' -> playRound(score, indexA, indexB, c);
            case 'B' -> playRound(score, indexB, indexA, c);
            default -> throw new IllegalCharsetNameException(String.valueOf(c));
        };
    }

    private String playRound(GameScore score, int indexWinner, int indexLooser, char c) {
        if (indexWinner < 3 || (indexWinner == 3 && indexLooser == 3))
            indexWinner++;
        else if ((indexWinner == 3 && indexLooser == 4))
            indexLooser--;
        else
            return "Player " + c + " wins the game";
        score.setScorePlayerA(c == 'A' ? scores.get(indexWinner) : scores.get(indexLooser));
        score.setScorePlayerB(c == 'B' ? scores.get(indexWinner) : scores.get(indexLooser));
        return score.toString();
    }


}
