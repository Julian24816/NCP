package w43;

public class MyThread extends Thread {
    private final int ID;

    public MyThread(int id) {
        ID = id;
    }

    @Override
    public void run() {
        System.out.println("New Thread #" + ID);
    }
}
