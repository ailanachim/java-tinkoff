package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static edu.hw1.Task5.isPalindromeDescendant;

public class Task5Test {
    @Test
    void isPalindromeDescendantTest() {
        assertThat(isPalindromeDescendant(0)).isEqualTo(true);
        assertThat(isPalindromeDescendant(1)).isEqualTo(true);
        assertThat(isPalindromeDescendant(-5)).isEqualTo(true);

        assertThat(isPalindromeDescendant(55)).isEqualTo(true);
        assertThat(isPalindromeDescendant(56)).isEqualTo(true);  // 56 -> 11
        assertThat(isPalindromeDescendant(-11)).isEqualTo(true);
        assertThat(isPalindromeDescendant(10)).isEqualTo(false);
        assertThat(isPalindromeDescendant(-67)).isEqualTo(false);

        assertThat(isPalindromeDescendant(232)).isEqualTo(true);
        assertThat(isPalindromeDescendant(431)).isEqualTo(true);  // 431 -> 44
        assertThat(isPalindromeDescendant(437)).isEqualTo(false);  // 437 -> 410 -> 41

        assertThat(isPalindromeDescendant(11211230)).isEqualTo(true);  // 11211230 -> 2333 -> 56 -> 11
        assertThat(isPalindromeDescendant(13001120)).isEqualTo(true);  // 13001120 -> 4022 ➞ 44
        assertThat(isPalindromeDescendant(23336014)).isEqualTo(true);  // 23336014 -> 5665
    }

}
