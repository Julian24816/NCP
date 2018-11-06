package w46;

import java.util.Vector;

class UniqueNumberList {
    private Vector<Integer> list = new Vector<>();
    void add(Integer i) {
        if (!list.contains(i))
            list.add(i);
    }
    void checkForRedundantNumber() {
        boolean foundDuplicate = false;

        Vector<Integer> copy = new Vector<>(list);

        for (int i : list) {
            copy.remove(i);
            if (copy.contains(i)) {
                foundDuplicate = true;
                System.out.println("Found duplicated number: " + i);
            }
        }
        if (!foundDuplicate) System.out.println("Didn't find any duplicate numbers.");
    }
}