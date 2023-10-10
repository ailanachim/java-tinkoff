package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static edu.hw1.Task3.isNestable;

public class Task3Test {

    @Test void isNestableTest() {
        int[] a = {};
        int[] b = {2, 1, 3, 4};
        int[] c = {0, 6};
        int[] d = {4, 2};

        assertThat(isNestable(a, a)).isFalse();
        assertThat(isNestable(a, b)).isFalse();
        assertThat(isNestable(null, b)).isFalse();
        assertThat(isNestable(c, a)).isFalse();
        assertThat(isNestable(c, null)).isFalse();

        assertThat(isNestable(b, c)).isTrue();
        assertThat(isNestable(c, b)).isFalse();

        assertThat(isNestable(b, d)).isFalse();
        assertThat(isNestable(d, b)).isFalse();

        assertThat(isNestable(d, c)).isTrue();
        assertThat(isNestable(c, d)).isFalse();
    }

}
