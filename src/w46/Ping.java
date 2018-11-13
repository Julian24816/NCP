package w46;

public class Ping extends Thread {
    private final PingPongGameState ppState;

    Ping(PingPongGameState pps) {
        this.ppState = pps;
    }

    @Override
    public void run() {
        while (!isInterrupted()) try {
            synchronized (ppState) {
                while (ppState.isPING()) {
                    ppState.wait();
                }
                ppState.setPING();
                ppState.notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
