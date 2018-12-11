package w50.codeExample;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class DemoForkJoinTaskDirty {
    public static void main (String [] args){
        ForkJoinPool    forkJoinPool = new ForkJoinPool(2);
        MyRecursiveTask myRecursiveTask = new MyRecursiveTask(15 *30);
        long result = forkJoinPool.invoke(myRecursiveTask);
        System.out.println("Result: " + result);
    }
}

