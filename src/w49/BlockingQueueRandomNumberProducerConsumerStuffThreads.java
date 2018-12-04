package w49;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockingQueueRandomNumberProducerConsumerStuffThreads {
    public static void main(String[] args) {
        BlockingQueue<Integer> list = new LinkedBlockingQueue<>();
        Thread producer = new Thread(() -> {
            Random random = new Random();
            while (!Thread.currentThread().isInterrupted()) try {
                list.put(random.nextInt(1000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        Thread consumer = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) try {
                System.out.println(list.take());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        producer.start();
        consumer.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("sleep interrupted");
        }
        producer.interrupt();
        consumer.interrupt();
        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            System.out.println("join interrupted");
        }
    }
}
