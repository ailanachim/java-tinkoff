package edu.hw3;

import org.junit.jupiter.api.Test;
import java.util.Map;
import static edu.hw3.Task3.freqDict;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class Task3Test {

    @Test
    void test() {
        assertThat(freqDict(new String[] {"a", "bb", "a", "bb"}))
            .isEqualTo(Map.of("bb", 2, "a", 2));

        assertThat(freqDict(new String[] {"this", "and", "that", "and"}))
            .isEqualTo(Map.of(
                "that", 1,
                "and", 2,
                "this", 1
            ));

        assertThat(freqDict(new String[] {"код", "код", "код", "bug"}))
            .isEqualTo(Map.of("код", 3, "bug", 1));

        assertThat(freqDict(new Integer[] {1, 1, 2, 2}))
            .isEqualTo(Map.of(1, 2, 2, 2));

        assertThat(freqDict(new String[] {})).isEqualTo(Map.of());
        assertThatThrownBy(() -> freqDict(null)).isInstanceOf(IllegalArgumentException.class);
    }
}
