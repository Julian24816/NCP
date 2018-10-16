package w43;

public class HelloThreadWorld {
    public static void main(String[] args) {
        try {
            // Thread-daughter-class
            System.out.println("Main Thread");
            Thread thread = new MyThread(1);
            thread.start();
            thread.join();

            // runnable
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    System.out.println("New Thread #2");
                }
            };
            thread = new Thread(task);
            thread.start();
            thread.join();

            // anonymous runnable
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("New Thread #3");
                }
            });
            thread.start();
            thread.join();

            // lambda expression
            thread = new Thread(() -> {
                System.out.println("New Thread #4");
            });
            thread.start();
            thread.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
