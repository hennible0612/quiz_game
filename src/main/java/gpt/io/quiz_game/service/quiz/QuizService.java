package gpt.io.quiz_game.service.quiz;


import com.fasterxml.jackson.databind.ObjectMapper;
import gpt.io.quiz_game.config.ChatGptConfig;
import gpt.io.quiz_game.dto.quiz.GptRequestDto;
import gpt.io.quiz_game.dto.quiz.GptResponseDto;
import gpt.io.quiz_game.dto.quiz.QuestionGameRequestDto;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class QuizService {
    private static RestTemplate restTemplate = new RestTemplate();

    public GptRequestDto askQuestion(QuestionGameRequestDto questionGameRequestDto) {
        try {
            GptRequestDto gptRequestDto = new GptRequestDto(
                    questionGameRequestDto,
                    ChatGptConfig.MODEL,
                    ChatGptConfig.BASE_TOKEN,
                    ChatGptConfig.TEMPERATURE,
                    ChatGptConfig.TOP_P
            );

            HttpHeaders headers = createHeaders();
            HttpEntity<GptRequestDto> entity = new HttpEntity<>(gptRequestDto, headers);
            ResponseEntity<String> response = restTemplate.exchange(ChatGptConfig.URL, HttpMethod.POST, entity, String.class);

            System.out.println(response);
            return gptRequestDto;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(ChatGptConfig.MEDIA_TYPE));
        headers.add(ChatGptConfig.AUTHORIZATION, ChatGptConfig.BEARER + ChatGptConfig.API_KEY);
        return headers;
    }
}
