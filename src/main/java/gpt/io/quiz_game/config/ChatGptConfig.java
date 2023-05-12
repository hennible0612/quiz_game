package gpt.io.quiz_game.config;

public class ChatGptConfig {
    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer ";
    public static final String API_KEY = "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";
    public static final String MODEL = "text-davinci-003";
//    public static final String MODEL = "gpt-3.5-turbo";
    public static final Integer MAX_TOKEN = 400;
    public static final Double TEMPERATURE = 0.0;
    public static final Double TOP_P = 1.0;
    public static final String MEDIA_TYPE = "application/json; charset=UTF-8";
    public static final String URL = "https://api.openai.com/v1/completions";
//    public static final String URL = "https://api.openai.com/v1/chat/completions";

}