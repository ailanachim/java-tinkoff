package edu.hw3;

import edu.hw3.task8.BackwardIterator;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task8Test {

    @Test
    void test1() {
        BackwardIterator<Integer> iterator = new BackwardIterator<>(List.of(1, 2, 3));

        assertThat(iterator.next()).isEqualTo(3);
        assertThat(iterator.next()).isEqualTo(2);
        assertThat(iterator.next()).isEqualTo(1);
        assertThat(iterator.hasNext()).isFalse();
    }

    @Test
    void test2() {
        BackwardIterator<Integer> iterator = new BackwardIterator<>(List.of());

        assertThat(iterator.hasNext()).isFalse();
    }
}
