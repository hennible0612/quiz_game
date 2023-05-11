package gpt.io.quiz_game.domain.room;

public enum HeadCount {
    TWO(2), FOUR(4), EIGHT(8);

    private int num;

    HeadCount(int num) {
        this.num = num;
    }
}
