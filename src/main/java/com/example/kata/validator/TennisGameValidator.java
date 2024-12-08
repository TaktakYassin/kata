package com.example.kata.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;

public class TennisGameValidator implements ConstraintValidator<TennisGameConstraint, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        int occurrenceA = StringUtils.countMatches(s, "A");
        int occurrenceB = StringUtils.countMatches(s, "B");
        if (s.length() <= 6)
            return occurrenceA == 4 || occurrenceB == 4;
        else
            return s.length() % 2 == 0 && Math.abs(occurrenceA - occurrenceB) == 2;
    }
}
