package gpt.io.quiz_game.service.quiz;


import gpt.io.quiz_game.config.ChatGptConfig;
import gpt.io.quiz_game.dto.quiz.QuestionRequestDto;
import gpt.io.quiz_game.dto.quiz.QuizDto;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.*;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class QuizService {
    private static RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity<Map<String, List<String>>> askQuestion(QuestionRequestDto requestDto) {
        HttpHeaders headers = createHeaders();
        HttpEntity<String> entity = new HttpEntity<>(createRequestBody(requestDto), headers);
        try {
//            ResponseEntity<String> response = restTemplate.exchange(ChatGptConfig.URL, HttpMethod.POST, entity, String.class);
//            System.out.println(response.getBody());
//            List<QuizDto> quizDto = parseResponse(response.getBody()); // Assuming parseResponse method returns a QuizDto object
//
//            return new ResponseEntity<>(quizDto, HttpStatus.OK); // Return a ResponseEntity with the QuizDto and an OK status
            ResponseEntity<String> response = restTemplate.exchange(ChatGptConfig.URL, HttpMethod.POST, entity, String.class);
            System.out.println(response.getBody());

            List<QuizDto> quizDtos = parseResponse(response.getBody());

            List<String> questions = new ArrayList<>();
            List<String> answers = new ArrayList<>();

            for (QuizDto quiz : quizDtos) {
                questions.add(quiz.getQuestion());
                answers.add(quiz.getAnswer());
            }

            Map<String, List<String>> responseMap = new HashMap<>();
            responseMap.put("questions", questions);
            responseMap.put("answers", answers);

            return new ResponseEntity<>(responseMap, HttpStatus.OK);
        } catch (HttpClientErrorException e) {
            System.out.println(e.getStatusCode());
            System.out.println(e.getResponseBodyAsString());
            return new ResponseEntity<>(null, e.getStatusCode()); // Return a ResponseEntity with the error status
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); // Return a ResponseEntity with a server error status
        }
    }

    private List<QuizDto> parseResponse(String responseBody) {

        JSONObject responseJson = new JSONObject(responseBody);
        JSONArray messages = responseJson.getJSONArray("choices");
        JSONObject message = messages.getJSONObject(0).getJSONObject("message");
        String content = message.getString("content");
        System.out.println(content);
        List<QuizDto> quizDtos = new ArrayList<>();

        String[] parts = content.split("\n");
        for (String part : parts) {
            String[] subParts = part.split(" \\(정답: ");
            if (subParts.length == 2) {
                String question = subParts[0].trim();
                String answer = subParts[1].substring(0, subParts[1].indexOf(")")).trim();

                QuizDto quiz = new QuizDto(question, answer);
                quizDtos.add(quiz);
            }
        }
        System.out.println(quizDtos);
        return quizDtos;
    }

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(ChatGptConfig.MEDIA_TYPE));
        headers.add(ChatGptConfig.AUTHORIZATION, ChatGptConfig.BEARER + ChatGptConfig.API_KEY);
        return headers;
    }

    private String createRequestBody(QuestionRequestDto requestDto) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("role", "user");
        jsonObject.put("content", requestDto.getRound() + "개의" + requestDto.getTopic() + "에 대한 퀴즈와 정답을 내줘, " +
                "답은 무조건 한단어야, 대답 형식은 다음과 같아 1. '질문' (정답: '정답')");

        JSONArray jsonArray = new JSONArray();
        jsonArray.put(jsonObject);

        JSONObject finalObject = new JSONObject();
        finalObject.put("model", ChatGptConfig.MODEL);
        finalObject.put("messages", jsonArray);
        finalObject.put("max_tokens", 300);

        return finalObject.toString();
    }


}
