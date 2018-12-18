package w51;

class Player {
    private static int totalPlayerCount = 0;
    private int score;
    private String name;

    private Player(int score, String name) {
        this.score = score;
        this.name = name;
    }

    static Player makeRandomPlayer() {
        return new Player(10,
                "random Player #" + ++totalPlayerCount);
    }

    int getScore() {
        return score;
    }
}
