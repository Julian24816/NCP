package w02;

import java.util.LinkedList;
import java.util.List;

public class NumberListRequester implements Runnable {

    private AtomicUniqueSequenceGenerator sequenceGenerator;
    private List<Integer> numbers = new LinkedList<>();
    private int maxInt;

    public NumberListRequester(AtomicUniqueSequenceGenerator sequenceGenerator, int maxInt) {
        this.sequenceGenerator = sequenceGenerator;
        this.maxInt = maxInt;
    }

    @Override
    public void run() {
        while(sequenceGenerator.getValue() < maxInt)
            numbers.add(sequenceGenerator.getNextValue());
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
