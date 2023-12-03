package edu.hw8;

import edu.hw8.task2.FiboCalculator;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {

    @Test
    void test() throws Exception {
        FiboCalculator calculator = new FiboCalculator();
        assertThat(calculator.calculate(0, 4)).isEqualTo(1);
        assertThat(calculator.calculate(1, 4)).isEqualTo(1);
        assertThat(calculator.calculate(2, 4)).isEqualTo(2);
        assertThat(calculator.calculate(3, 4)).isEqualTo(3);
        assertThat(calculator.calculate(4, 4)).isEqualTo(5);
        assertThat(calculator.calculate(5, 4)).isEqualTo(8);
        assertThat(calculator.calculate(6, 4)).isEqualTo(13);
        assertThat(calculator.calculate(7, 4)).isEqualTo(21);
        assertThat(calculator.calculate(8, 4)).isEqualTo(34);
        assertThat(calculator.calculate(9, 4)).isEqualTo(55);
        assertThat(calculator.calculate(10, 4)).isEqualTo(89);
    }
}
