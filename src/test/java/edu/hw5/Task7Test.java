package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task7Test {

    @Test
    @DisplayName("String contains no less 3 chars and third is 0")
    void test1() {
        assertThat(Task7.notLess3CharsAndThirdIs0(null)).isFalse();
        assertThat(Task7.notLess3CharsAndThirdIs0("")).isFalse();
        assertThat(Task7.notLess3CharsAndThirdIs0("210")).isFalse();

        assertThat(Task7.notLess3CharsAndThirdIs0("11")).isFalse();
        assertThat(Task7.notLess3CharsAndThirdIs0("101")).isFalse();
        assertThat(Task7.notLess3CharsAndThirdIs0("1011")).isFalse();

        assertThat(Task7.notLess3CharsAndThirdIs0("110")).isTrue();
        assertThat(Task7.notLess3CharsAndThirdIs0("0100")).isTrue();
        assertThat(Task7.notLess3CharsAndThirdIs0("11011101111")).isTrue();
    }

    @Test
    @DisplayName("String starts and ends with same char")
    void test2() {
        assertThat(Task7.startsAndEndsWithSame(null)).isFalse();
        assertThat(Task7.startsAndEndsWithSame("")).isFalse();
        assertThat(Task7.startsAndEndsWithSame("2")).isFalse();
        assertThat(Task7.startsAndEndsWithSame("aa")).isFalse();

        assertThat(Task7.startsAndEndsWithSame("10")).isFalse();
        assertThat(Task7.startsAndEndsWithSame("0101")).isFalse();

        assertThat(Task7.startsAndEndsWithSame("1")).isTrue();
        assertThat(Task7.startsAndEndsWithSame("0")).isTrue();
        assertThat(Task7.startsAndEndsWithSame("101")).isTrue();
        assertThat(Task7.startsAndEndsWithSame("11111")).isTrue();
        assertThat(Task7.startsAndEndsWithSame("00")).isTrue();
    }

    @Test
    @DisplayName("String has length between 1 and 3")
    void test3() {
        assertThat(Task7.lengthBetween1And3(null)).isFalse();
        assertThat(Task7.lengthBetween1And3("")).isFalse();
        assertThat(Task7.lengthBetween1And3("2")).isFalse();
        assertThat(Task7.lengthBetween1And3("aa")).isFalse();
        assertThat(Task7.lengthBetween1And3("0101")).isFalse();

        assertThat(Task7.lengthBetween1And3("1")).isTrue();
        assertThat(Task7.lengthBetween1And3("0")).isTrue();
        assertThat(Task7.lengthBetween1And3("101")).isTrue();
        assertThat(Task7.lengthBetween1And3("00")).isTrue();
    }
}
