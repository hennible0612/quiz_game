package gpt.io.quiz_game.dto.quiz;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Choice {

    private String text;
    private Integer index;
    @JsonProperty("finish_reason")
    private String finishReason;

    public Choice(String text, Integer index, String finishReason) {
        this.text = text;
        this.index = index;
        this.finishReason = finishReason;
    }
}
