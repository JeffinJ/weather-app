package com.jeffin.cityfinder.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class LetterQueryRequest {
    @NotBlank(message = "Letter cannot be blank")
    @Pattern(regexp = "^[a-zA-Z]$", message = "Must be a single alphabetic character")
    private String letter;

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }
}