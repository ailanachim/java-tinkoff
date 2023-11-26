package edu.hw7;

import edu.hw7.task4.ParallelPiApproximator;
import edu.hw7.task4.PiApproximator;
import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {

    @Test
    void test() throws InterruptedException {
        var approximator = new PiApproximator(1000);
        var approximator2 = new PiApproximator(1000_000);

        assertThat(approximator.calculatePi()).isCloseTo(Math.PI, Percentage.withPercentage(5));
        assertThat(approximator2.calculatePi()).isCloseTo(Math.PI, Percentage.withPercentage(0.1));
    }

    @Test
    void multiThreadTest() throws InterruptedException {
        var approximator = new ParallelPiApproximator(1000, 4);
        var approximator2 = new ParallelPiApproximator(1000_000, 4);

        assertThat(approximator.calculatePi()).isCloseTo(Math.PI, Percentage.withPercentage(5));
        assertThat(approximator2.calculatePi()).isCloseTo(Math.PI, Percentage.withPercentage(0.1));
    }
}
