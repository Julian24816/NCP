package w46;

import java.util.LinkedList;
import java.util.List;

class Tester extends Thread {
    private UniqueSequenceGenerator uniqueSequenceGenerator;
    List<Integer> attainedIDs = new LinkedList<>();

    Tester(UniqueSequenceGenerator uniqueSequenceGenerator) {
        this(uniqueSequenceGenerator, null);
    }
    Tester(UniqueSequenceGenerator uniqueSequenceGenerator, Tester anotherTester) {
        this.uniqueSequenceGenerator = uniqueSequenceGenerator;

    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            attainedIDs.add(uniqueSequenceGenerator.getNext());
        }
    }
}
