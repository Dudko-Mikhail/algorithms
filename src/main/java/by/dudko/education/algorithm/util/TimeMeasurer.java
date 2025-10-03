package by.dudko.education.algorithm.util;

import java.util.LongSummaryStatistics;
import java.util.concurrent.Callable;

public class TimeMeasurer {
    private final LongSummaryStatistics statistics = new LongSummaryStatistics();

    public void measure(Runnable action) {
        long start = System.nanoTime();
        action.run();
        statistics.accept(System.nanoTime() - start);
    }

    public <T> T measure(Callable<T> action) {
        long start = System.nanoTime();
        T result = null;
        try {
            result = action.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        statistics.accept(System.nanoTime() - start);
        return result;
    }


    public LongSummaryStatistics getStatistics() {
        return statistics;
    }
}
