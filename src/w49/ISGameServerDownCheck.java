package w49;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ISGameServerDownCheck {
    private static Random random = new Random();

    public static void main(String[] args) throws IOException {
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

        ScheduledFuture<?> scheduledTask = scheduledExecutorService.scheduleAtFixedRate(
                () -> System.out.println(random.nextInt(2) == 0 ? "game server down" : "still connected."),
                0, 30, TimeUnit.SECONDS);

        System.out.println("Press Enter to exit game");
        System.in.read();
        scheduledTask.cancel(true);
        scheduledExecutorService.shutdownNow();
    }
}
