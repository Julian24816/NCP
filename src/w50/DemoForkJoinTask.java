package w50;

import java.util.concurrent.ForkJoinPool;

public class DemoForkJoinTask {
    public static void main (String [] args){
        System.out.println("Result: " + getResult());
    }

    private static long getResult() {
        ForkJoinPool forkJoinPool = new ForkJoinPool(2);
        MyRecursiveTask myRecursiveTask = new MyRecursiveTask(450);
        return forkJoinPool.invoke(myRecursiveTask);
    }
}

