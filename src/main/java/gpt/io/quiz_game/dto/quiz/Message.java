package gpt.io.quiz_game.dto.quiz;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
public class Message implements Serializable {
    private String role;
    private String content;

    @Builder
    public Message(String role, String content) {
        this.role = role;
        this.content = content;
    }
}