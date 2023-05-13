package gpt.io.quiz_game.controller.quiz;

import gpt.io.quiz_game.dto.quiz.QuestionRequestDto;
import gpt.io.quiz_game.service.quiz.QuizService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping("/quiz")
//    ResponseEntity<List<QuizDto>>
    public ResponseEntity<Map<String, List<String>>> sendQuestion(@RequestBody QuestionRequestDto requestDto) {
        return quizService.askQuestion(requestDto);
    }
}
