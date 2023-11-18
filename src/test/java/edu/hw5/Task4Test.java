package edu.hw5;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {

    @Test
    void checkPasswordTest() {
        assertThat(Task4.checkPassword(null)).isFalse();
        assertThat(Task4.checkPassword("")).isFalse();
        assertThat(Task4.checkPassword("abcd")).isFalse();

        assertThat(Task4.checkPassword("~")).isTrue();
        assertThat(Task4.checkPassword("!")).isTrue();
        assertThat(Task4.checkPassword("@")).isTrue();
        assertThat(Task4.checkPassword("#")).isTrue();
        assertThat(Task4.checkPassword("$")).isTrue();
        assertThat(Task4.checkPassword("%")).isTrue();
        assertThat(Task4.checkPassword("^")).isTrue();
        assertThat(Task4.checkPassword("&")).isTrue();
        assertThat(Task4.checkPassword("*")).isTrue();
        assertThat(Task4.checkPassword("|")).isTrue();

        assertThat(Task4.checkPassword("a~b")).isTrue();
        assertThat(Task4.checkPassword("ab|")).isTrue();
        assertThat(Task4.checkPassword("!abc")).isTrue();
        assertThat(Task4.checkPassword("#*@chvj")).isTrue();
    }
}
