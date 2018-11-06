package w46;

class Tester extends Thread {
    private UniqueSequenceGenerator uniqueSequenceGenerator;
    private Tester another;
    private int lastID;

    Tester(UniqueSequenceGenerator uniqueSequenceGenerator) {
        this(uniqueSequenceGenerator, null);
    }
    Tester(UniqueSequenceGenerator uniqueSequenceGenerator, Tester anotherTester) {
        this.uniqueSequenceGenerator = uniqueSequenceGenerator;
        this.another = anotherTester;
        this.lastID = 0;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            lastID = uniqueSequenceGenerator.getNext();
            if (another != null && another.lastID == lastID)
                System.out.println("duplicate Value attained: " + lastID);
        }
    }
}
