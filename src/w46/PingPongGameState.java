package w46;

public class PingPongGameState {
    private enum Turns {
        PING,
        PONG
    }
    private Turns state = Turns.PONG;

    public boolean isPING() {
        return state == Turns.PING;
    }

    public boolean isPONG() {
        return state == Turns.PONG;
    }

    public void setPING() {
        System.out.println("PING");
        state = Turns.PING;
    }

    public void setPONG() {
        System.out.println("PONG");
        state = Turns.PONG;
    }

    public static void main(String[] args) {
        try {
            PingPongGameState ppState = new PingPongGameState();
            Ping ping = new Ping(ppState);
            Pong pong = new Pong(ppState);
            ping.start();
            pong.start();
            Thread.sleep(10);
            ping.interrupt();
            pong.interrupt();
            ping.join();
            pong.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
