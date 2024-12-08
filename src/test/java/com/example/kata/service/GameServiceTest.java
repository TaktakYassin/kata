package com.example.kata.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.IllegalCharsetNameException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class GameServiceTest {

    @Autowired
    private GameService gameService;

    @Test
    void test_playGame_when_input_is_valid_sucess() {
        List<String> result = gameService.playGame("ABABAA");
        List<String> expected = Arrays.asList(
                "Player A : 15 / Player B : 0",
                "Player A : 15 / Player B : 15",
                "Player A : 30 / Player B : 15",
                "Player A : 30 / Player B : 30",
                "Player A : 40 / Player B : 30",
                "Player A wins the game"
        );
        assertEquals(expected, result);
    }

    @Test
    void test_playGame_when_invalid_input_throw_exception() {
        assertThrows(
                IllegalCharsetNameException.class,
                () -> {
                    gameService.playGame("toto");
                }
        );
    }

    @Test
    void test_playGame_with_deuce_rule_sucess() {
        List<String> result = gameService.playGame("ABABABABAA");
        List<String> expected = Arrays.asList(
                "Player A : 15 / Player B : 0",
                "Player A : 15 / Player B : 15",
                "Player A : 30 / Player B : 15",
                "Player A : 30 / Player B : 30",
                "Player A : 40 / Player B : 30",
                "Player A : 40 / Player B : 40",
                "Player A : A / Player B : 40",
                "Player A : 40 / Player B : 40",
                "Player A : A / Player B : 40",
                "Player A wins the game"
        );
        assertEquals(expected, result);
    }


}