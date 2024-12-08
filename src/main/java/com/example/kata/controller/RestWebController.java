package com.example.kata.controller;

import com.example.kata.validator.TennisGameConstraint;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.example.kata.service.GameService;

import java.util.List;

@RestController
@RequestMapping("api")
@Validated
@Slf4j
public class RestWebController {

    @Autowired
    private GameService gameService;

    @PostMapping(value = "/display-tennis-game")
    public ResponseEntity<List<String>> playGame(
            @RequestParam
            @Size(min = 4)
            @Pattern(regexp = "^[A-B]*$")
            @TennisGameConstraint
            String input) {
        return ResponseEntity.ok(gameService.playGame(input));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List<String>> handleConstraintViolationException(ConstraintViolationException cve) {
        List<String> errorMessages = cve.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .toList();

        return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
    }
}
