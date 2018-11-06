package w46;

public class UniqueSequenceGenerator {
    private int value = 0;
    synchronized int getNext() {
        return value++;
    }

    public static void main(String[] args) {
        try {
            UniqueSequenceGenerator usg = new UniqueSequenceGenerator();
            Tester tester1 = new Tester(usg);
            Tester tester2 = new Tester(usg, tester1);
            tester1.start();
            tester2.start();
            System.out.println("#1");

            Thread.sleep(500);

            System.out.println("#2");
            tester1.interrupt();
            tester2.interrupt();
            System.out.println("#3");
            tester1.join();
            tester2.join();
            System.out.println("#4");

            lookForDuplicates(tester1, tester2);
            System.out.println("#5");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void lookForDuplicates(Tester tester1, Tester tester2) {
        long length = (long) tester1.attainedIDs.size();
        System.out.println(length);
        for (int i : tester1.attainedIDs) {
            if (i%1000 == 0) System.out.println(length + " #"+i);
            if (tester2.attainedIDs.contains(i))
                System.out.println("duplicate ID attained " + i);
        }
    }
}
