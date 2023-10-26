package edu.hw3;

import org.junit.jupiter.api.Test;
import static edu.hw3.Task4.convertToRoman;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class Task4Test {

    @Test
    void test() {
        assertThat(convertToRoman(1)).isEqualTo("I");
        assertThat(convertToRoman(2)).isEqualTo("II");
        assertThat(convertToRoman(3)).isEqualTo("III");
        assertThat(convertToRoman(4)).isEqualTo("IV");
        assertThat(convertToRoman(5)).isEqualTo("V");
        assertThat(convertToRoman(6)).isEqualTo("VI");
        assertThat(convertToRoman(7)).isEqualTo("VII");
        assertThat(convertToRoman(8)).isEqualTo("VIII");
        assertThat(convertToRoman(9)).isEqualTo("IX");
        assertThat(convertToRoman(10)).isEqualTo("X");

        assertThat(convertToRoman(12)).isEqualTo("XII");
        assertThat(convertToRoman(16)).isEqualTo("XVI");
        assertThat(convertToRoman(19)).isEqualTo("XIX");
        assertThat(convertToRoman(61)).isEqualTo("LXI");
        assertThat(convertToRoman(347)).isEqualTo("CCCXLVII");
        assertThat(convertToRoman(1983)).isEqualTo("MCMLXXXIII");
        assertThat(convertToRoman(5064)).isEqualTo("MMMMMLXIV");

        assertThatThrownBy(() -> convertToRoman(0)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> convertToRoman(-5)).isInstanceOf(IllegalArgumentException.class);
    }
}
