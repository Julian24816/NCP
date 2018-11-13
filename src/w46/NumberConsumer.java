package w46;

class NumberConsumer extends Thread{
    private final NumberQueue queue;
    NumberConsumer(NumberQueue queue) {
        this.queue = queue;
        queue.addConsumer(this);
    }

    void producerStopped() {
        interrupt();
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                System.out.println("got Number: " + queue.getNumber());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
