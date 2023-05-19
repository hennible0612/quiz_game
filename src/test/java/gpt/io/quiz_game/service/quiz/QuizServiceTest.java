package gpt.io.quiz_game.service.quiz;

import gpt.io.quiz_game.dto.quiz.QuestionGameRequestDto;
import gpt.io.quiz_game.dto.quiz.QuizDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class QuizServiceTest {

    @InjectMocks
    private QuizService quizService;

    @Mock
    private RestTemplate restTemplate;

    private QuestionGameRequestDto requestDto;

    @BeforeEach
    public void setUp() {
        requestDto = new QuestionGameRequestDto();
        requestDto.setRound(3);
        requestDto.setTopic("경제");
    }
    @Test
    public void testAskQuestion() {
        String mockResponseBody = "{\"id\":\"chatcmpl-7G25oHxfNPKm2spZzKvUOcCINbYcv\",\"object\":\"chat.completion\",\"created\":1684055648,\"model\":\"gpt-3.5-turbo-0301\",\"usage\":{\"prompt_tokens\":76,\"completion_tokens\":138,\"total_tokens\":214},\"choices\":[{\"message\":{\"role\":\"assistant\",\"content\":\"1. 선진국들이 무엇? (정답: 산업화)\\n2. 경제적 무엇 하나요? (정답: 자유시장)\\n3. 경제적 무엇이라고 하나요? (정답: 경제정책)\"},\"finish_reason\":\"stop\",\"index\":0}]}";
        ResponseEntity<String> mockResponse = ResponseEntity.ok(mockResponseBody);

        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), eq(String.class)))
                .thenReturn(mockResponse);
    }

    //parse test
    @Test
    public void testParseTest() {
        // Given
        QuestionGameRequestDto requestDto = new QuestionGameRequestDto(); // populate this with appropriate values
        requestDto.setRound(3);
        requestDto.setTopic("경제");

        String mockResponseBody = "{\"id\":\"chatcmpl-7G25oHxfNPKm2spZzKvUOcCINbYcv\",\"object\":\"chat.completion\",\"created\":1684055648,\"model\":\"gpt-3.5-turbo-0301\",\"usage\":{\"prompt_tokens\":76,\"completion_tokens\":138,\"total_tokens\":214},\"choices\":[{\"message\":{\"role\":\"assistant\",\"content\":\"1. 선진국들이 무엇? (정답: 산업화)\\n2. 경제적 무엇 하나요? (정답: 자유시장)\\n3. 경제적 무엇이라고 하나요? (정답: 경제정책)\"},\"finish_reason\":\"stop\",\"index\":0}]}";

        ResponseEntity<String> mockResponse = new ResponseEntity<>(mockResponseBody, HttpStatus.OK);

        // When
        List<QuizDto> quizDtos = quizService.parseResponse(mockResponse.getBody());

        //Then
        assertNotNull(quizDtos);
        assertEquals(3, quizDtos.size());

        QuizDto quizDto1 = quizDtos.get(0);
        assertEquals("1. 선진국들이 무엇?", quizDto1.getQuestion());
        assertEquals("산업화", quizDto1.getAnswer());

        QuizDto quizDto2 = quizDtos.get(1);
        assertEquals("2. 경제적 무엇 하나요?", quizDto2.getQuestion());
        assertEquals("자유시장", quizDto2.getAnswer());

        QuizDto quizDto3 = quizDtos.get(2);
        assertEquals("3. 경제적 무엇이라고 하나요?", quizDto3.getQuestion());
        assertEquals("경제정책", quizDto3.getAnswer());

    }




}