package gpt.io.quiz_game.dto.quiz;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
public class MessageDto implements Serializable {
    private String role;
    private String content;

}