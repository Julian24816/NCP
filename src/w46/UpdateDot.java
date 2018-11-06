package w46;

public class UpdateDot implements Runnable {
    private final Dot d;
    private int i;

    private UpdateDot(Dot d, int i) {
        this.d = d;
        this.i = i;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            synchronized (d) {
                d.setX(i);
                d.setY(i);
                if (d.getX() != d.getY()) {
                    System.out.println("Warning! Race condition! Difference: Thread " + Thread.currentThread().getName() +
                            " | X = " + d.getX() + " | Y = " + d.getY());
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            Dot dot = new Dot(0, 0);
            Thread thread1 = new Thread(new UpdateDot(dot, 1));
            Thread thread2 = new Thread(new UpdateDot(dot, 10));
            thread1.setName("T1");
            thread2.setName("T2");
            thread1.start();
            thread2.start();

            Thread.sleep(3000);

            thread1.interrupt();
            thread2.interrupt();
            thread1.join();
            thread2.join();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
