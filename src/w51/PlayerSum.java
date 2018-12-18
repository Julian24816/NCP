package w51;

import w48.Stopwatch;

import java.util.stream.Stream;

public class PlayerSum {
    private static final long MAX_PLAYER_GENERATED = 5_000L;

    public static void main(String[] args) {
        benchmarkParallelBlock();
        benchmarkSerialBlock();
        benchmarkParallelBlock();
        benchmarkSerialBlock();
    }

    private static void benchmarkParallelBlock() {
        benchmarkParallel();
        benchmarkParallel();
        benchmarkParallel();
        benchmarkParallel();
        System.out.println();
    }

    private static void benchmarkSerialBlock() {
        benchmarkSerial();
        benchmarkSerial();
        benchmarkSerial();
        benchmarkSerial();
        System.out.println();
    }

    private static void benchmarkSerial() {
        Stopwatch stopwatch;
        Stream<Player> playerStream;
        stopwatch = new Stopwatch();
        playerStream = getPlayerStream();
        stopwatch.reset();
        System.out.println(MAX_PLAYER_GENERATED + " players summed serial:   " +
                playerStream.mapToInt(Player::getScore).sum() +
                " after " + stopwatch + "s");
    }

    private static void benchmarkParallel() {
        Stopwatch stopwatch;
        stopwatch = new Stopwatch();
        Stream<Player> playerStream = getPlayerStream();
        stopwatch.reset();
        System.out.println(MAX_PLAYER_GENERATED + " players summed parallel: " +
                playerStream.parallel().mapToInt(Player::getScore).sum() +
                " after " + stopwatch + "s");
    }

    private static Stream<Player> getPlayerStream() {
        return Stream.generate(Player::makeRandomPlayer)
                .limit(MAX_PLAYER_GENERATED);
    }
}
