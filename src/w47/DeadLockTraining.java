package w47;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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

    private final Lock lock1 = new ReentrantLock();
    private final Lock lock2 = new ReentrantLock();
    private int i = 0;

    void ping() {
        if (lock1.tryLock()) {
            try {
                System.out.println("PING got Monitor 1");
                if (lock2.tryLock()) {
                    try {

                        System.out.println("PING got Monitor 2" + ++i);
                    } finally {
                        lock2.unlock();
                    }
                }
            } finally {
                lock1.unlock();
            }
        }
    }

    void pong() {
        if (lock2.tryLock()) {
            try {
                System.out.println("PONG got Monitor 2");
                if (lock1.tryLock()) {
                    try {
                        System.out.println("PONG got Monitor 1" + ++i);
                    } finally {
                        lock1.unlock();
                    }
                }
            } finally {
                lock2.unlock();
            }
        }
    }
}
