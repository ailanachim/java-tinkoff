package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static edu.hw1.Task7.rotateLeft;
import static edu.hw1.Task7.rotateRight;

public class Task7Test {

    @Test void rotateLeftTest() {
        assertThat(rotateLeft(0, 1)).isEqualTo(0);
        assertThat(rotateLeft(1, 2)).isEqualTo(1);
        assertThat(rotateLeft(7, 1)).isEqualTo(7);
        assertThat(rotateLeft(7, 2)).isEqualTo(7);
        assertThat(rotateLeft(7, 3)).isEqualTo(7);

        assertThat(rotateLeft(8, 1)).isEqualTo(1);
        assertThat(rotateLeft(8, 3)).isEqualTo(4);
        assertThat(rotateLeft(17, 2)).isEqualTo(6);
        assertThat(rotateLeft(17, 6)).isEqualTo(3);
    }

    @Test void rotateRightTest() {
        assertThat(rotateRight(0, 1)).isEqualTo(0);
        assertThat(rotateRight(1, 2)).isEqualTo(1);
        assertThat(rotateRight(7, 1)).isEqualTo(7);
        assertThat(rotateRight(7, 2)).isEqualTo(7);
        assertThat(rotateRight(7, 3)).isEqualTo(7);

        assertThat(rotateRight(8, 1)).isEqualTo(4);
        assertThat(rotateRight(8, 3)).isEqualTo(1);
        assertThat(rotateRight(8, 4)).isEqualTo(8);
        assertThat(rotateRight(17, 2)).isEqualTo(12);
        assertThat(rotateRight(17, 6)).isEqualTo(24);
    }

}
