package w02;

import java.util.concurrent.atomic.AtomicInteger;

class AtomicUniqueSequenceGenerator {
    private AtomicInteger value = new AtomicInteger(0);
    int getNextValue() {
        return value.incrementAndGet();
    }
    int getValue() {
        return value.get();
    }
}
