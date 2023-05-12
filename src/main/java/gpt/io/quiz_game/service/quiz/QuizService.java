package gpt.io.quiz_game.service.quiz;


import gpt.io.quiz_game.config.ChatGptConfig;
import gpt.io.quiz_game.dto.quiz.GptRequestDto;
import gpt.io.quiz_game.dto.quiz.GptResponseDto;
import gpt.io.quiz_game.dto.quiz.QuestionRequestDto;
import org.springframework.http.*;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class QuizService {
    private static RestTemplate restTemplate = new RestTemplate();

    public HttpEntity<GptRequestDto> buildHttpEntity(GptRequestDto requestDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(ChatGptConfig.MEDIA_TYPE));
        headers.add(ChatGptConfig.AUTHORIZATION, ChatGptConfig.BEARER + ChatGptConfig.API_KEY);
        return new HttpEntity<>(requestDto, headers);
    }

    public GptResponseDto getResponse(HttpEntity<GptRequestDto> chatGptRequestDtoHttpEntity) {
        ResponseEntity<GptResponseDto> responseEntity = restTemplate.postForEntity(
                ChatGptConfig.URL,
                chatGptRequestDtoHttpEntity,
                GptResponseDto.class);

        return responseEntity.getBody();
    }
    public String createString(Integer round, String topic) {
        return round +" 만큼의 질문과 " + topic + "에 관하여 퀴즈를 만들어줘, 답은 무조건 한단어 한글로 답변해줘";
    }
    public GptResponseDto askQuestion(QuestionRequestDto requestDto) {
        return this.getResponse(
                this.buildHttpEntity(
                        new GptRequestDto(
                                ChatGptConfig.MODEL,
                                createString(requestDto.getRound(), requestDto.getTopic()),
                                ChatGptConfig.MAX_TOKEN,
                                ChatGptConfig.TEMPERATURE,
                                ChatGptConfig.TOP_P
                        )
                )
        );
    }
}
