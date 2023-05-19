package gpt.io.quiz_game.dto.quiz;

import com.fasterxml.jackson.annotation.JsonProperty;
import gpt.io.quiz_game.config.ChatGptConfig;
import lombok.Getter;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
public class GptRequestDto implements Serializable {
    private String role;
    private String content;
    private String model;
    private List<JSONObject> messages;
    @JsonProperty("max_tokens")
    private Integer maxTokens;
    private double temperature;
    @JsonProperty("top_p")
    private double topP;

    public GptRequestDto(QuestionGameRequestDto requestDto, String model, Integer maxTokens, double temperature, double topP) {
        JSONObject jsonObject = new JSONObject();

        this.role = "user";
        this.content = requestDto.getRound() + "개의" + requestDto.getTopic() + "에 대한 퀴즈와 정답을 내줘, " +
                "답은 무조건 한단어야, 대답 형식은 다음과 같아 1. '질문' : (정답: '정답') ";

        jsonObject.put("role", this.role);
        jsonObject.put("content", this.content);

        this.messages = new ArrayList<>();
        this.messages.add(jsonObject);

        this.model = model;
        this.maxTokens = maxTokens;
        this.temperature = temperature;
        this.topP = topP;
    }

}
