package com.example.game.api;

import com.example.game.AdditionQuestion;
import com.example.game.AdditionQuestionService;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class AdditionQuestionServiceTest {
    @Autowired AdditionQuestionService additionQuestionService;

    @Test void canGetQueston() {
        JsonNode node = additionQuestionService.getQuestion();

        System.out.println(node);
        assertTrue(node.get("number1").asInt() < 10);
        assertTrue(node.get("number2").asInt() < 10);
    }

    @Test void returnsTrueWhenGuessIsCorrect() {
        //given:
        //when:
        //then:
        int guess = 3;
        AdditionQuestion question = new AdditionQuestion(1, 2);
        JsonNode node = additionQuestionService.getCorrectAnswer(guess, question);

        assertEquals(node.get("guess").asInt(), guess);
        assertEquals(node.get("isCorrect").asBoolean(), true);
        assertEquals(node.get("question").get("number1").asInt(), 1);
        assertEquals(node.get("question").get("number2").asInt(), 2);
        assertEquals(node.get("question").get("question-string").asText(), "1 + 2 = ?");
    }

    @Test void returnsFalseWhenGuessIsNotCorrect() {
        //given:
        //when:
        //then:

        int guess = 5;
        AdditionQuestion question = new AdditionQuestion(1, 2);
        JsonNode node = additionQuestionService.getCorrectAnswer(guess, question);


        assertEquals(node.get("guess").asInt(), guess);
        assertEquals(node.get("isCorrect").asBoolean(), false);
        assertEquals(node.get("question").get("number1").asInt(), 1);
        assertEquals(node.get("question").get("number2").asInt(), 2);
        assertEquals(node.get("question").get("question-string").asText(), "1 + 2 = ?");
    }
}
