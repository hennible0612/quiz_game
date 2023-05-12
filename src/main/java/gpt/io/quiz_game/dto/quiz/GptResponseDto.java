package gpt.io.quiz_game.dto.quiz;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
public class GptResponseDto implements Serializable {
        private String id;
        private String object;
        private LocalDate created;
        private String model;
        private List<Choice> choices;

        @Builder
        public GptResponseDto(String id, String object,
                                  LocalDate created, String model,
                                  List<Choice> choices) {
                this.id = id;
                this.object = object;
                this.created = created;
                this.model = model;
                this.choices = choices;
        }
}
