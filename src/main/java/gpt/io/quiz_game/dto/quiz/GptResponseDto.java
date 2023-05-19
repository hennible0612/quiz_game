package gpt.io.quiz_game.dto.quiz;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GptResponseDto {

    private String id;
    private String object;
    private LocalDate created;
    private String model;
    private List<Choice> choices;
}
