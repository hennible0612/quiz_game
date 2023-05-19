package gpt.io.quiz_game.config;

public class ChatGptConfig {
    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer ";
    public static final String API_KEY = "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";
//    public static final String MODEL = "gpt-4";
    public static final String MODEL = "gpt-3.5-turbo";
    public static final Integer BASE_TOKEN = 100;
    public static final Double TEMPERATURE = 0.5;
    public static final Double TOP_P = 0.5;
    public static final String MEDIA_TYPE = "application/json; charset=UTF-8";
    public static final String URL = "https://api.openai.com/v1/chat/completions";

}