package edu.hw9.task1;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class StatsCollector {

    private final Map<String, Metric> stats = new ConcurrentHashMap<>();
    private final ExecutorService executor = Executors.newFixedThreadPool(4);

    public void push(String metricName, double[] data) throws ExecutionException, InterruptedException {
        if (metricName == null) {
            throw new IllegalArgumentException();
        }

        Future<Metric> future = executor.submit(() -> Metric.create(data));

        Metric result = future.get();
        stats.put(metricName, result);
    }

    public Collection<Map.Entry<String, Metric>> stats() {
        return stats.entrySet();
    }
}
