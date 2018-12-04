package w49;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.*;

public class ISGameServerDownCheck {
    private static Random random = new Random();

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

        ScheduledFuture<?> scheduledTask = scheduledExecutorService.scheduleAtFixedRate(
                () -> System.out.println(random.nextBoolean() ? "game server down" : "still connected."),
                0, 1, TimeUnit.SECONDS);
        scheduledExecutorService.schedule(() -> scheduledTask.cancel(true), 10, TimeUnit.SECONDS);

        try {scheduledTask.get();} catch (CancellationException ignored) {}
        scheduledExecutorService.shutdownNow();
    }
}
