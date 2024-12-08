package com.example.kata.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameScore {

    private String scorePlayerA;
    private String scorePlayerB;

    @Override
    public String toString() {
        return "Player A : " + scorePlayerA +
                " / Player B : " + scorePlayerB;
    }
}
