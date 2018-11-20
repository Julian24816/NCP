package w47;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class NumberQueue {
    private List<NumberConsumer> consumerList = new LinkedList<>();
    private List<Integer> numberList = new LinkedList<>();

    private Lock lock = new ReentrantLock();
    private Condition onQueueNotEmpty = lock.newCondition();
    private Condition onQueueNotFull = lock.newCondition();

    synchronized void addConsumer(NumberConsumer numberConsumer) {
        consumerList.add(numberConsumer);
    }

    synchronized void stopConsumers() {
        for (NumberConsumer numberConsumer : consumerList)
            numberConsumer.producerStopped();
    }

    void addNumber(int i) throws InterruptedException {
        lock.lockInterruptibly();
        try {
            while (numberList.size() >= 4) onQueueNotFull.await();
            numberList.add(i);
            onQueueNotEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }

    int getNumber() throws InterruptedException {
        lock.lockInterruptibly();
        try {
            while (numberList.size() == 0) onQueueNotEmpty.await();
            int i = numberList.remove(0);
            onQueueNotFull.signalAll();
            return i;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        try {
            NumberQueue queue = new NumberQueue();
            NumberProducer producer = new NumberProducer(queue);

            NumberConsumer[] consumers = new NumberConsumer[3];
            for (int i = 0; i< consumers.length; i++)
                consumers[i] = new NumberConsumer(queue);

            producer.start();
            for (NumberConsumer consumer : consumers)
                consumer.start();

            producer.join();
            for (NumberConsumer consumer : consumers)
                consumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
