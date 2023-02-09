package com.example.game;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@Slf4j
public class AdditionQuestionController {

    public static final String QUESTION_API_PATH = "/math/addition/question";
    public static final String GUESS_API_PATH = "/math/addition/guess";
    @Autowired AdditionQuestionService additionQuestionService;

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @GetMapping(QUESTION_API_PATH)
    public JsonNode getAdditionQuestion() {
        log.info("Request Received: [{}] [{}]", GET, QUESTION_API_PATH);
        return additionQuestionService.getQuestion();
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @GetMapping(GUESS_API_PATH)
    public JsonNode isGuessCorrect(@RequestParam String guess,
                                   @RequestParam String number1,
                                   @RequestParam String number2) {
        log.info("Request Received: [{}] [{}] -- [{}]", GET, GUESS_API_PATH, ImmutableMap.of("guess", guess, "n1", number1, "n2", number2));
        return additionQuestionService.getCorrectAnswer(Integer.parseInt(guess), new AdditionQuestion(Integer.parseInt(number1), Integer.parseInt(number2)));
    }
}
