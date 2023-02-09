package com.example.game;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AdditionQuestion {
    @JsonProperty private int number1;
    @JsonProperty private int number2;

    public AdditionQuestion(int number1, int number2) {
        this.number1 = number1;
        this.number2 = number2;
    }

    @JsonProperty("question-string")
    public String toString() {
        return this.number1 + " + " + this.number2 + " = ?";
    }

    @JsonIgnore public int getCorrectAnswer() {
        return this.number1 + this.number2;
    }

    public boolean isGuessCorrect(int guess) {
        return guess == getCorrectAnswer();
    }
}
