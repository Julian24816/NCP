package w42;

import java.util.Random;

public class RandomThreadSleep {

    private static class RandomSleepThread extends Thread {
        private final int ID;
        private Random r = new Random();

        private RandomSleepThread(int id) {
            ID = id;
        }

        @Override
        public void run() {
            try {
                long sleepTime, startTime, endTime;

                System.out.println("Hello, World! - from New Thread - Application ID [" + ID + "]  (JVM ID [" + getId() + "])");

                // execution sequence hack
                sleep(100 * (ID + 1));

                sleepTime = r.nextInt(5000);
                System.out.println("Thread AppID [" + ID + "] will sleep for " + sleepTime + " milli-seconds (random - max 5 seconds)");
                startTime = System.currentTimeMillis();

                sleep(sleepTime);

                endTime = System.currentTimeMillis();
                System.out.println("Thread AppID [" + ID + "] finished sleeping (actual time sleeping : " + (endTime - startTime) + " ms)");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            return "AppID [" + ID + "] JVM name: " + getName();
        }
    }

    public static void main(String[] args) {
        System.out.println(">> Main Thread Starting...\n");

        System.out.println("Hello, World! - from Main Thread JVM name : " + Thread.currentThread().getName() + "\n");

        try {
            RandomSleepThread thread1 = new RandomSleepThread(0),
                    thread2 = new RandomSleepThread(1),
                    thread3 = new RandomSleepThread(2);

            System.out.println("3 New Threads Created :\n");
            System.out.println(thread1);
            System.out.println(thread2);
            System.out.println(thread3);
            System.out.println();

            System.out.println(">> New Threads Starting...\n");
            thread1.start();
            thread2.start();
            thread3.start();

            thread1.join();
            thread2.join();
            thread3.join();
            System.out.println("\n<< All New Threads Finished\n");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("<< Main Thread Finished");
    }
}
