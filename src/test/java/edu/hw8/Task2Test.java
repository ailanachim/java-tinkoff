package edu.hw8;

import edu.hw8.task2.FiboCalculator;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {

    @Test
    void test() throws InterruptedException {
        assertThat(new FiboCalculator(4).calculate(0)).isEqualTo(1);
        assertThat(new FiboCalculator(4).calculate(1)).isEqualTo(1);
        assertThat(new FiboCalculator(4).calculate(2)).isEqualTo(2);
        assertThat(new FiboCalculator(4).calculate(3)).isEqualTo(3);
        assertThat(new FiboCalculator(4).calculate(4)).isEqualTo(5);
        assertThat(new FiboCalculator(4).calculate(5)).isEqualTo(8);
        assertThat(new FiboCalculator(4).calculate(6)).isEqualTo(13);
        assertThat(new FiboCalculator(4).calculate(7)).isEqualTo(21);
        assertThat(new FiboCalculator(4).calculate(8)).isEqualTo(34);
        assertThat(new FiboCalculator(4).calculate(9)).isEqualTo(55);
        assertThat(new FiboCalculator(4).calculate(10)).isEqualTo(89);
    }
}
