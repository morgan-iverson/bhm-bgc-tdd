package com.example.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdditionQuestionTest {
    @Test void canGetCorrectAnswer() {
        //given:
        //when:
        //then:

        AdditionQuestion question = new AdditionQuestion(1, 2);
        int actualResponse = question.getCorrectAnswer();
        int expectedResponse = 3;

        assertEquals(actualResponse, expectedResponse);
    }

    @Test void returnsTrueWhenGuessIsCorrect() {
        //given:
        //when:
        //then:

        AdditionQuestion question = new AdditionQuestion(1, 2);
        int guess = 3;
        boolean actualResponse = question.isGuessCorrect(guess);
        boolean expectedResponse = true;

        assertEquals(actualResponse, expectedResponse);
    }

    @Test void returnsFalseWhenGuessIsNotCorrect() {
        //given:
        //when:
        //then:

        AdditionQuestion question = new AdditionQuestion(1, 2);
        int guess = 5;
        boolean actualResponse = question.isGuessCorrect(guess);
        boolean expectedResponse = false;

        assertEquals(actualResponse, expectedResponse);
    }

    @Test void canGetQuestionString() {
        //given:
        //when:
        //then:

        AdditionQuestion question = new AdditionQuestion(1, 2);

        String actualResponse = question.toString();
        String expectedResponse = "1 + 2 = ?";

        assertEquals(actualResponse, expectedResponse);
    }
}
