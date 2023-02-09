package com.example.game;

import com.example.game.AdditionQuestion;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.ImmutableMap;
import org.springframework.stereotype.Service;

import java.util.Random;

import static com.example.game.util.Utils.OBJECT_MAPPER;

@Service
public class AdditionQuestionService {
    public JsonNode getQuestion() {
        return OBJECT_MAPPER.valueToTree(new AdditionQuestion(getNumber(), getNumber()));
    }

    public JsonNode getCorrectAnswer(int guess, AdditionQuestion additionQuestion) {
        return OBJECT_MAPPER.valueToTree(ImmutableMap.of(
            "guess", guess,
            "correct-answer", additionQuestion.getCorrectAnswer(),
            "is-correct", additionQuestion.isGuessCorrect(guess),
            "question", additionQuestion));
    }

    public static int getNumber() {
        return new Random().nextInt(10);
    }
}
