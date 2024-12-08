package com.example.kata.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = TennisGameValidator.class)
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface TennisGameConstraint {
    String message() default "Invalid tennis game input";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
