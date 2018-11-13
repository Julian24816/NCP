package w46;

public class Pong extends Thread {
    private final PingPongGameState ppState;

    Pong(PingPongGameState pps) {
        this.ppState = pps;
    }

    @Override
    public void run() {
        while (!isInterrupted()) try {
            synchronized (ppState) {
                while (ppState.isPONG()) {
                    ppState.wait();
                }
                ppState.setPONG();
                ppState.notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
