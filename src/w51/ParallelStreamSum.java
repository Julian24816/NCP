package w51;

import java.util.stream.IntStream;

public class ParallelStreamSum {
    public static void main(String[] args) {
        System.out.println(IntStream.generate(() -> 1)
                .limit(500_000_000L)
                .boxed()
                .parallel()
                .mapToInt(Integer::intValue)
                .sum());
    }
}
