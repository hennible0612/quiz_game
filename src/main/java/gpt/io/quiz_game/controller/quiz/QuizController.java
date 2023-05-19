package gpt.io.quiz_game.controller.quiz;

import gpt.io.quiz_game.dto.quiz.GptResponseDto;
import gpt.io.quiz_game.dto.quiz.QuizGameRequestDto;
import gpt.io.quiz_game.service.quiz.QuizService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("/quiz")
    public GptResponseDto sendQuestion(@RequestBody QuizGameRequestDto requestDto) {

        return quizService.askQuestion(requestDto);
    }
}
