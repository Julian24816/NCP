package w48;

public class Stopwatch {
    private long startTime;

    public Stopwatch() {
        reset();
    }

    public void reset() {
        this.startTime = System.nanoTime();
    }

    @Override
    public String toString() {
        return String.valueOf((System.nanoTime() - startTime) / 1_000_000_000.);
    }
}
