package gpt.io.quiz_game.dto.quiz;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class QuizGameRequestDto {
    private String topic;
    private Integer round;


}