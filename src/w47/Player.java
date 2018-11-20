package w47;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Player extends Thread {
    private final w47.PingPongGameState ppState;
    private final w47.PingPongGameState.Turns myTurn;
    private final Lock lock;
    private final Condition waitToPlay;

Player(PingPongGameState pps, w47.PingPongGameState.Turns turn, Lock lock, Condition waitToPlay) {
        this.ppState = pps;
        this.myTurn = turn;
        this.lock = lock;
        this.waitToPlay = waitToPlay;
    }

    @Override
    public void run() {
        while (!isInterrupted()) try {
            lock.lockInterruptibly();
            while (ppState.isState(myTurn)) {
                waitToPlay.await();
            }
            ppState.setState(myTurn);
            waitToPlay.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
            interrupt();
        } finally {
            lock.unlock();
        }
    }
}
