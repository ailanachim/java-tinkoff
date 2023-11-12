package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task8Test {
    @Test
    @DisplayName("String length is odd")
    void test1() {
        assertThat(Task8.lengthIsOdd(null)).isFalse();
        assertThat(Task8.lengthIsOdd("")).isFalse();

        assertThat(Task8.lengthIsOdd("a")).isFalse();
        assertThat(Task8.lengthIsOdd("213")).isFalse();
        assertThat(Task8.lengthIsOdd("11")).isFalse();

        assertThat(Task8.lengthIsOdd("0")).isTrue();
        assertThat(Task8.lengthIsOdd("101")).isTrue();
        assertThat(Task8.lengthIsOdd("11000")).isTrue();
    }

    @Test
    @DisplayName("String starts with 0 and has odd length or starts with 1 and has even length")
    void test2() {
        assertThat(Task8.check2(null)).isFalse();
        assertThat(Task8.check2("")).isFalse();

        assertThat(Task8.check2("023")).isFalse();
        assertThat(Task8.check2("1234")).isFalse();

        assertThat(Task8.check2("0")).isTrue();
        assertThat(Task8.check2("11")).isTrue();
        assertThat(Task8.check2("1101")).isTrue();
        assertThat(Task8.check2("0111010")).isTrue();
    }

    @Test
    @DisplayName("Count of 0 is divided by 3")
    void test3() {
        assertThat(Task8.check3(null)).isFalse();
        assertThat(Task8.check3("")).isTrue();

        assertThat(Task8.check3("00023")).isFalse();
        assertThat(Task8.check3("1234")).isFalse();

        assertThat(Task8.check3("1")).isTrue();
        assertThat(Task8.check3("00011")).isTrue();
        assertThat(Task8.check3("11010110")).isTrue();
        assertThat(Task8.check3("0111010010110")).isTrue();
    }

    @Test
    @DisplayName("Any string but not 11 or 111")
    void test4() {
        assertThat(Task8.check4(null)).isFalse();
        assertThat(Task8.check4("")).isTrue();

        assertThat(Task8.check4("00023")).isFalse();
        assertThat(Task8.check4("1234")).isFalse();

        assertThat(Task8.check4("11")).isFalse();
        assertThat(Task8.check4("111")).isFalse();

        assertThat(Task8.check4("1")).isTrue();
        assertThat(Task8.check4("110")).isTrue();
        assertThat(Task8.check4("011")).isTrue();
        assertThat(Task8.check4("1110")).isTrue();
        assertThat(Task8.check4("1111")).isTrue();
        assertThat(Task8.check4("11010110")).isTrue();
    }

    @Test
    @DisplayName("Every odd char is 1")
    void test5() {
        assertThat(Task8.check5(null)).isFalse();
        assertThat(Task8.check5("")).isTrue();

        assertThat(Task8.check5("10123")).isFalse();
        assertThat(Task8.check5("1214")).isFalse();

        assertThat(Task8.check5("100")).isFalse();
        assertThat(Task8.check5("01101")).isFalse();

        assertThat(Task8.check5("1")).isTrue();
        assertThat(Task8.check5("101")).isTrue();
        assertThat(Task8.check5("11111")).isTrue();
        assertThat(Task8.check5("10111110")).isTrue();
    }

    @Test
    @DisplayName("String contains no less two 0 and no more one 1")
    void test6() {
        assertThat(Task8.check6(null)).isFalse();
        assertThat(Task8.check6("")).isFalse();

        assertThat(Task8.check6("100023")).isFalse();
        assertThat(Task8.check6("00120")).isFalse();

        assertThat(Task8.check6("11")).isFalse();
        assertThat(Task8.check6("01")).isFalse();
        assertThat(Task8.check6("1")).isFalse();
        assertThat(Task8.check6("10")).isFalse();
        assertThat(Task8.check6("10110")).isFalse();

        assertThat(Task8.check6("100")).isTrue();
        assertThat(Task8.check6("0001")).isTrue();
        assertThat(Task8.check6("00")).isTrue();
        assertThat(Task8.check6("010")).isTrue();
        assertThat(Task8.check6("00000001")).isTrue();
        assertThat(Task8.check6("1000000")).isTrue();
        assertThat(Task8.check6("000000000")).isTrue();
        assertThat(Task8.check6("000100")).isTrue();
    }

    @Test
    @DisplayName("String doesn't contain sequential 1")
    void test7() {
        assertThat(Task8.check7(null)).isFalse();
        assertThat(Task8.check7("")).isTrue();

        assertThat(Task8.check7("1213")).isFalse();
        assertThat(Task8.check7("00120")).isFalse();

        assertThat(Task8.check7("11")).isFalse();
        assertThat(Task8.check7("11111")).isFalse();
        assertThat(Task8.check7("0110101")).isFalse();

        assertThat(Task8.check7("1")).isTrue();
        assertThat(Task8.check7("101010101")).isTrue();
        assertThat(Task8.check7("010")).isTrue();
        assertThat(Task8.check7("01001001")).isTrue();
        assertThat(Task8.check7("100100")).isTrue();
    }
}
