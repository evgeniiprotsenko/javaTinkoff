package edu.hw9.Task1;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class StatsCollector implements Closeable {

    private final Map<String, List<Double>> dataMap;
    private final ExecutorService executor;
    private final static int EXECUTOR_THREAD_COUNT = 4;

    public StatsCollector() {
        this.dataMap = new HashMap<>();
        this.executor = Executors.newFixedThreadPool(EXECUTOR_THREAD_COUNT);
    }

    public void push(String metricName, double[] data) {
        executor.submit(() -> {
            synchronized (dataMap) {
                dataMap.putIfAbsent(metricName, new ArrayList<>());
                List<Double> values = dataMap.get(metricName);
                for (double value : data) {
                    values.add(value);
                }
                dataMap.put(metricName, values);
            }
        });
    }

    public List<String> stats() throws InterruptedException, ExecutionException {
        synchronized (dataMap) {
            List<Future<String>> futures = new ArrayList<>();

            for (Map.Entry<String, List<Double>> entry : dataMap.entrySet()) {
                Future<String> future = executor.submit(() -> {
                    String metricName = entry.getKey();
                    List<Double> values = entry.getValue();

                    double sum = values.stream().mapToDouble(Double::doubleValue).sum();
                    double average = sum / values.size();
                    double max = values.stream().mapToDouble(Double::doubleValue).max().orElse(0);
                    double min = values.stream().mapToDouble(Double::doubleValue).min().orElse(0);

                    return String.format("Metric: %s, Sum: %f, Average: %f, Max: %f, Min: %f",
                        metricName, sum, average, max, min
                    );
                });

                futures.add(future);
            }

            List<String> result = new ArrayList<>();
            for (Future<String> future : futures) {
                result.add(future.get());
            }

            return result;
        }
    }



    @Override
    public void close() {
        executor.shutdown();
    }
}
