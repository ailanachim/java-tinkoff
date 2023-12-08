package edu.hw9;

import edu.hw9.task1.Metric;
import edu.hw9.task1.StatsCollector;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {

    @Test
    void test() throws InterruptedException {
        StatsCollector collector = new StatsCollector();

        Thread thread1 = new Thread(() -> {
            try {
                collector.push("temperature", new double[] {10, 15, 20, 17, 18});
            } catch (ExecutionException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                collector.push("pressure", new double[] {720, 730, 731, 728});
            } catch (ExecutionException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread thread3 = new Thread(() -> {
            try {
                collector.push("wind_speed", new double[] {2, 3, 4, 10});
            } catch (ExecutionException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        assertThat(collector.stats().contains(Map.entry("temperature", new Metric(80, 16, 10, 20)))).isTrue();
        assertThat(collector.stats().contains(Map.entry("pressure", new Metric(2909, 727.25, 720, 731)))).isTrue();
        assertThat(collector.stats().contains(Map.entry("wind_speed", new Metric(19, 4.75, 2, 10)))).isTrue();
    }
}
