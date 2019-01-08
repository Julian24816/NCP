package w02;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class A1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        AtomicUniqueSequenceGenerator generator = new AtomicUniqueSequenceGenerator();
        int maxInt = 10000;

        NumberListRequester list1 = new NumberListRequester(generator, maxInt);
        NumberListRequester list2 = new NumberListRequester(generator, maxInt);
        runListRequesters(list1, list2);
        compareLists(list1.getNumbers(), list2.getNumbers());
        System.out.println("Done.");
    }

    private static void compareLists(List<Integer> list1, List<Integer> list2) {
        for (int i : list1)
            if (list2.contains(i))
                System.out.println("found duplicate number: " + i);
    }

    private static void runListRequesters(NumberListRequester list1, NumberListRequester list2) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future result1 = executorService.submit(list1);
        Future result2 = executorService.submit(list2);
        result1.get();
        result2.get();
    }
}
