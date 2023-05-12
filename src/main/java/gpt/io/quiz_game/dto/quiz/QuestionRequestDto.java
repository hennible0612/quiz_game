package gpt.io.quiz_game.dto.quiz;

import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class QuestionRequestDto implements Serializable {
    private String topic;
    private Integer round;


}