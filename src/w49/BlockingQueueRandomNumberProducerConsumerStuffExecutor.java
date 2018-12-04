package w49;

import java.util.Random;
import java.util.concurrent.*;

public class BlockingQueueRandomNumberProducerConsumerStuffExecutor {
    public static void main(String[] args) {
        BlockingQueue<Integer> list = new LinkedBlockingQueue<>();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(() -> {
            Random random = new Random();
            while (!Thread.currentThread().isInterrupted()) try {
                list.put(random.nextInt(1000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        executorService.submit(() -> {
            while (!Thread.currentThread().isInterrupted()) try {
                System.out.println(list.take());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("sleep interrupted");
        }
        executorService.shutdownNow();
        try {
            executorService.awaitTermination(1000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
