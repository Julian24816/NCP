package w47;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class DeadLockTraining {
    public static void main(String[] args) {
        try {
            PingPong theGame = new PingPong();
            Thread ping = new Thread(() -> {
                for (int i = 0; i < 10000; i++) {
                    theGame.ping();
                    System.out.println("I pinged.");
                }
            });

            Thread pong = new Thread(() -> {
                for (int i = 0; i < 10000; i++) {
                    theGame.pong();
                    System.out.println("I ponged.");
                }
            });
            ping.start();
            pong.start();
            Thread.sleep(1000);
            ping.interrupt();
            pong.interrupt();
            ping.join();
            pong.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


class PingPong {

    private final Object Monitor1 = new Object();
    private final Object Monitor2 = new Object();
    private int i = 0;

    void ping() {
        synchronized (Monitor1) {
            System.out.println("PING got Monitor 1");
            synchronized (Monitor2) {
                System.out.println("PING got Monitor 2" + ++i);
            }
        }
    }

    void pong() {
        synchronized (Monitor2) {
            System.out.println("PONG got Monitor 2");
            synchronized (Monitor1) {
                System.out.println("PONG got Monitor 1" + ++i);
            }
        }
    }
}
