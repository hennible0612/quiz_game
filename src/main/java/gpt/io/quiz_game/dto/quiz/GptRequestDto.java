package gpt.io.quiz_game.dto.quiz;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@NoArgsConstructor
public class GptRequestDto implements Serializable {
    private String model;
    private String prompt;
    @JsonProperty("max_tokens")
    private Integer maxTokens;
    private Double temperature;
    @JsonProperty("top_p")
    private Double topP;
    @Builder
    public GptRequestDto(String model, String prompt,
                             Integer maxTokens, Double temperature,
                             Double topP) {
        this.model = model;
        this.prompt = prompt;
        this.maxTokens = maxTokens;
        this.temperature = temperature;
        this.topP = topP;
    }

}
