package w48;

import java.util.Arrays;
import java.util.concurrent.Semaphore;

public class SemaphoreThreads extends Thread {

    private Semaphore semaphore;

    public SemaphoreThreads(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        while (!isInterrupted()) try {
            semaphore.acquire();
            System.out.println("Hello World from Thread " + getName());
        } catch (InterruptedException e) {
            //e.printStackTrace();
            interrupt();
        } finally {
            semaphore.release();
        }
    }

    public static void main(String[] args) {
        try {
            Semaphore semaphore = new Semaphore(4);
            SemaphoreThreads[] threads = new SemaphoreThreads[8];
            for (int i = 0; i < threads.length; i++)
                threads[i] = new SemaphoreThreads(semaphore);

            for (SemaphoreThreads thread : threads)
                thread.start();

            Thread.sleep(1000);

            for (SemaphoreThreads thread : threads)
                thread.interrupt();
            for (SemaphoreThreads thread : threads)
                thread.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
