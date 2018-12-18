package w51;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CrazyThreads {
    public static void main(String[] args) throws InterruptedException {
        Runnable infiniteLoop = () -> {while (!Thread.currentThread().isInterrupted());};
        ExecutorService test = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*2);
        for (int i = 0; i < Runtime.getRuntime().availableProcessors()*2; i++)
            test.submit(infiniteLoop);
        Thread.sleep(60000);
        test.shutdownNow();
    }
}
