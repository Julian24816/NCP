package w45;

import java.util.Vector;

class UniqueNumberList {
    private Vector<Integer> list = new Vector<>();
    synchronized void add(Integer i) {
        if (!list.contains(i))
            list.add(i);
    }
    void checkForRedundantNumber() {
        boolean foundDuplicate = false;

        for (int i : list) {
            int count = 0;
            for (int j : list)
                if (i==j)
                    count++;
            if (count > 1) {
                foundDuplicate = true;
                System.out.println("Found " + count + " instances of " + i);
            }
        }
        if (!foundDuplicate) System.out.println("Didn't find any duplicate numbers.");
    }
}