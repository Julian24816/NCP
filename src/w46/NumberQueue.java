package w46;

import java.util.LinkedList;
import java.util.List;

class NumberQueue {
    private List<NumberConsumer> consumerList = new LinkedList<NumberConsumer>();
    private List<Integer> numberList = new LinkedList<Integer>();

    synchronized void addConsumer(NumberConsumer numberConsumer) {
        consumerList.add(numberConsumer);
    }

    synchronized void stopConsumers() {
        for (NumberConsumer numberConsumer : consumerList)
            numberConsumer.producerStopped();
    }

    synchronized void addNumber(int i) throws InterruptedException {
        while (numberList.size() >= 4) wait();
        numberList.add(i);
        notifyAll();
    }

    synchronized int getNumber() throws InterruptedException {
        while (numberList.size() == 0) wait();
        int i = numberList.remove(0);
        notifyAll();
        return i;
    }

    public static void main(String[] args) {
        try {
            NumberQueue queue = new NumberQueue();
            NumberProducer producer = new NumberProducer(queue);
            NumberConsumer consumer = new NumberConsumer(queue);
            producer.start();
            consumer.start();
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
