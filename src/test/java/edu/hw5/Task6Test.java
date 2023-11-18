package edu.hw5;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task6Test {

    @Test
    void isSubsequenceTest() {
        assertThat(Task6.isSubsequence(null, "ab")).isFalse();
        assertThat(Task6.isSubsequence("abc", null)).isFalse();
        assertThat(Task6.isSubsequence(null, null)).isFalse();

        assertThat(Task6.isSubsequence("", "")).isTrue();
        assertThat(Task6.isSubsequence("", "ab")).isTrue();
        assertThat(Task6.isSubsequence("ab", "")).isFalse();

        assertThat(Task6.isSubsequence("abc", "achfdbaabgabcaabg")).isTrue();
        assertThat(Task6.isSubsequence("hagc", "achfdbaabgabcaabg")).isTrue();
        assertThat(Task6.isSubsequence("bcac", "achfdbaabgabcaabg")).isFalse();
    }
}
