package gpt.io.quiz_game.controller.quiz;

import gpt.io.quiz_game.dto.quiz.GptRequestDto;
import gpt.io.quiz_game.dto.quiz.GptResponseDto;
import gpt.io.quiz_game.dto.quiz.QuestionRequestDto;
import gpt.io.quiz_game.service.quiz.QuizService;
import org.springframework.http.*;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping("/quiz")
    public GptResponseDto sendQuestion(@RequestBody QuestionRequestDto requestDto) {
        return quizService.askQuestion(requestDto);
    }

//    @GetMapping
//    public ResponseEntity<GptResponseDto> getQuizQuestions(@RequestParam int rounds, @RequestParam String topic) {
//
//
//        RestTemplate restTemplate = new RestTemplate();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Authorization", "Bearer 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");
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        String prompt = generatePrompt(rounds, topic);
//
//        GptRequestDto gptRequest = new GptRequestDto();
//        gptRequest.setModel("gpt-3.5-turbo");
//        gptRequest.setPrompt(prompt);
//        gptRequest.setMaxTokens(200);
//
//        HttpEntity<GptRequestDto> entity = new HttpEntity<>(gptRequest, headers);
//
//        String gptUri = "https://api.openai.com/v1/models/text-davinci-003";
//
//        ResponseEntity<GptResponseDto> responseEntity = restTemplate.postForEntity(gptUri, entity, GptResponseDto.class);
//
//        return responseEntity;
//    }
//
//    private String generatePrompt(int rounds, String topic) {
//        // 여기서 퀴즈 질문을 생성하는 로직을 구현하세요.
//        return "Generate " + rounds + " questions about " + topic;
//    }
}
