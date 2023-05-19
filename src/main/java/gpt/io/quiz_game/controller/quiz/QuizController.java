package gpt.io.quiz_game.controller.quiz;

import gpt.io.quiz_game.dto.quiz.GptResponseDto;
import gpt.io.quiz_game.dto.quiz.QuestionGameRequestDto;
import gpt.io.quiz_game.service.quiz.QuizService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

//    @PostMapping("/quiz")
////    ResponseEntity<List<QuizDto>>
//    public ResponseEntity<Map<String, List<String>>> sendQuestion(@RequestBody QuestionGameRequestDto requestDto) {
//        return quizService.askQuestion(requestDto);
//    }
    @PostMapping("/quiz")
    public GptResponseDto sendQuestion(@RequestBody QuestionGameRequestDto requestDto) {

//        System.out.println(quizService.askQuestion(requestDto));
        return quizService.askQuestion(requestDto);
        //        return quizService.askQuestion(requestDto);
    }
}
