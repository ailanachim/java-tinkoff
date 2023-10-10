package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static edu.hw1.Task2.countDigits;

public class Task2Test {

    @Test void countDigitsTest() {
        assertThat(countDigits(0)).isEqualTo(1);
        assertThat(countDigits(6)).isEqualTo(1);
        assertThat(countDigits(10)).isEqualTo(2);
        assertThat(countDigits(45567678)).isEqualTo(8);
        assertThat(countDigits(-545676)).isEqualTo(6);
    }

}
