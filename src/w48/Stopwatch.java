package w48;

class Stopwatch {
    private long startTime;

    Stopwatch() {
        reset();
    }

    void reset() {
        this.startTime = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return String.valueOf((System.currentTimeMillis() - startTime) / 1000.);
    }
}
