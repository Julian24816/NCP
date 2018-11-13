package w46;

public class NumberProducer extends Thread {
    private final NumberQueue queue;
    NumberProducer(NumberQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            int i = 0;
            while (!isInterrupted()) {
                if (i>=1000) break;
                queue.addNumber(i++);
            }
            queue.stopConsumers();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
