package w02;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicUniqueSequenceGenerator {
    private AtomicInteger value = new AtomicInteger(0);
    public int getNextValue() {
        return value.incrementAndGet();
    }
    public int getValue() {
        return value.get();
    }
}
