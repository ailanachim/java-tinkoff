package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static edu.hw1.Task6.countK;

public class Task6Test {
    @Test void countKTest() {
        assertThat(countK(1)).isEqualTo(5);
        assertThat(countK(6621)).isEqualTo(5);
        assertThat(countK(6554)).isEqualTo(4);
        assertThat(countK(1234)).isEqualTo(3);

        assertThat(countK(0)).isEqualTo(0);
        assertThat(countK(6174)).isEqualTo(0);
        assertThat(countK(7777)).isEqualTo(1);
    }
}

