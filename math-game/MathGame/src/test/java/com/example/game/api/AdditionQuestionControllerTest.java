package com.example.game.api;

import com.example.game.AdditionQuestionService;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.game.AdditionQuestionController.GUESS_API_PATH;
import static com.example.game.AdditionQuestionController.QUESTION_API_PATH;
import static com.example.game.util.Utils.OBJECT_MAPPER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AdditionQuestionControllerTest extends BaseTest{
    @Autowired AdditionQuestionService additionQuestionService;
    public static final Pattern QUESTION_STRING_PATTERN = Pattern.compile("([0-9])\\s\\+\\s([0-9])\\s\\=\\s\\?");

    @Test void canGetQueston() throws Exception {
        final MvcResult result = mockMvcGet(QUESTION_API_PATH)
            .andExpect(status().isOk())
            .andReturn();

        JsonNode responseNode = OBJECT_MAPPER.readTree(result.getResponse().getContentAsString());
        Matcher questionStringMatcherResult = QUESTION_STRING_PATTERN.matcher(responseNode.get("question-string").asText());

        assertTrue(responseNode.get("number1").asInt() < 10);
        assertTrue(responseNode.get("number2").asInt() < 10);

        assertThat(questionStringMatcherResult.find()).isTrue();
        assertThat(questionStringMatcherResult.group(1)).isEqualTo(responseNode.get("number1").asText());
        assertThat(questionStringMatcherResult.group(2)).isEqualTo(responseNode.get("number2").asText());
    }

    @Test void returnsTrueWhenGuessisCorrect() throws Exception {
        //given:
        //when:
        //then:
        int guess = 3;
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("guess", String.valueOf(guess));
        requestParams.add("number1", String.valueOf(1));
        requestParams.add("number2", String.valueOf(2));

        final MvcResult result = mockMvcGet(GUESS_API_PATH, requestParams)
            .andExpect(status().isOk())
            .andReturn();

        JsonNode responseNode = OBJECT_MAPPER.readTree(result.getResponse().getContentAsString());

        assertEquals(responseNode.get("guess").asInt(), guess);
        assertEquals(responseNode.get("is-correct").asBoolean(), true);
        assertEquals(responseNode.get("correct-answer").asInt(), 3);
        assertEquals(responseNode.get("question").get("number1").asInt(), 1);
        assertEquals(responseNode.get("question").get("number2").asInt(), 2);
        assertEquals(responseNode.get("question").get("question-string").asText(), "1 + 2 = ?");
    }

    @Test void returnsFalseWhenGuessIsNotCorrect() throws Exception {
        //given:
        //when:
        //then:
        int guess = 5;
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("guess", String.valueOf(guess));
        requestParams.add("number1", String.valueOf(1));
        requestParams.add("number2", String.valueOf(2));

        final MvcResult result = mockMvcGet(GUESS_API_PATH, requestParams)
            .andExpect(status().isOk())
            .andReturn();

        JsonNode responseNode = OBJECT_MAPPER.readTree(result.getResponse().getContentAsString());

        assertEquals(responseNode.get("guess").asInt(), guess);
        assertEquals(responseNode.get("is-correct").asBoolean(), false);
        assertEquals(responseNode.get("correct-answer").asInt(), 3);
        assertEquals(responseNode.get("question").get("number1").asInt(), 1);
        assertEquals(responseNode.get("question").get("number2").asInt(), 2);
        assertEquals(responseNode.get("question").get("question-string").asText(), "1 + 2 = ?");
    }
}
