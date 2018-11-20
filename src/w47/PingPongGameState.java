package w47;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PingPongGameState {
    public enum Turns {
        PING,
        PONG
    }
    private Turns state = Turns.PONG;

    boolean isState(Turns turn) {
        return state == turn;
    }

    void setState(Turns turn) {
        System.out.println(turn);
        state = turn;
    }

    public static void main(String[] args) {
        try {
            Lock lock = new ReentrantLock();
            Condition waitToPlay = lock.newCondition();
            PingPongGameState ppState = new PingPongGameState();
            Player ping = new Player(ppState, Turns.PING, lock, waitToPlay);
            Player pong = new Player(ppState, Turns.PONG, lock, waitToPlay);
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
