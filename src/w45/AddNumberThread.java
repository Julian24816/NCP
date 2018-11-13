package w45;

import java.util.LinkedList;
import java.util.List;

public class AddNumberThread extends Thread {
    private UniqueNumberList numberList;

    private AddNumberThread(UniqueNumberList list) {
        numberList = list;
    }

    @Override
    public void run() {
        int i = 0;
        while (!isInterrupted()) numberList.add(i++);
    }

    public static void main(String[] args) {
        try {
            UniqueNumberList numberList = new UniqueNumberList();

            List<Thread> threads = new LinkedList<>();
            for (int i = 0; i < 10; i++) {
                threads.add(new AddNumberThread(numberList));
            }
            threads.forEach(Thread::start);

            Thread.sleep(2000);

            threads.forEach(Thread::interrupt);
            for (Thread thread : threads) {
                thread.join();
            }

            numberList.checkForRedundantNumber();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
