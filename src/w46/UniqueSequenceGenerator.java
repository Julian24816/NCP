package w46;

public class UniqueSequenceGenerator {
    private int value = 0;
    synchronized int getNext() {
        return value++;
    }

    public static void main(String[] args) {
        try {
            UniqueSequenceGenerator usg = new UniqueSequenceGenerator();
            Tester thread1 = new Tester(usg);
            Thread thread2 = new Tester(usg, thread1);
            thread1.start();
            thread2.start();

            Thread.sleep(3000);

            thread1.interrupt();
            thread2.interrupt();
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
