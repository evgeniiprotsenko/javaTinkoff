package edu.hw9.Task1;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.concurrent.ExecutionException;
import static org.junit.jupiter.api.Assertions.*;

class StatsCollectorTest {

    private final StatsCollector statsCollector = new StatsCollector();

    @Test
    void testStatsCollector() throws InterruptedException, ExecutionException {
        // Push some data
        statsCollector.push("metric1", new double[]{1.0, 2.0, 3.0});
        statsCollector.push("metric2", new double[]{0.5, 1.5, 2.5});

        Thread.sleep(100);

        List<String> result = statsCollector.stats();
        assertEquals(2, result.size());

        assertEquals("Metric: metric1, Sum: 6,000000, Average: 2,000000, Max: 3,000000, Min: 1,000000", result.get(0));
        assertEquals("Metric: metric2, Sum: 4,500000, Average: 1,500000, Max: 2,500000, Min: 0,500000", result.get(1));
    }

}
