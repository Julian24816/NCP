package w45;

import java.io.IOException;

public class InterruptPatternTraining {

    private static class MyThread extends Thread {
        @Override
        public void run() {
            while (!isInterrupted()) try {
                sleep(300);
                System.out.println("Hello, World");
            } catch (InterruptedException e) {
                e.printStackTrace();
                interrupt();
            }
        }
    }

    public static void main(String[] args) {
        try {

            // interrupt after 50ms
            MyThread myThread = new MyThread();
            myThread.start();
            Thread.sleep(50);
            myThread.interrupt();
            myThread.join();

            // interrupt after 300ms
            myThread = new MyThread();
            myThread.start();
            Thread.sleep(300);
            myThread.interrupt();
            myThread.join();

            // interrupt after random sleep time
            myThread = new MyThread();
            myThread.start();
            Thread.sleep((int) (Math.random() * 1000));
            myThread.interrupt();
            myThread.join();

            // interrupt after user input
            myThread = new MyThread();
            myThread.start();
            System.in.read();
            myThread.interrupt();
            myThread.join();

        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}
