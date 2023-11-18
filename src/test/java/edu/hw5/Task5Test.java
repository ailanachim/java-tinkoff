package edu.hw5;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task5Test {

    @Test
    void test() {
        assertThat(Task5.isValidCarNumber(null)).isFalse();
        assertThat(Task5.isValidCarNumber("")).isFalse();

        assertThat(Task5.isValidCarNumber("А123ВЕ777")).isTrue();
        assertThat(Task5.isValidCarNumber("О777ОО177")).isTrue();
        assertThat(Task5.isValidCarNumber("О777ОО77")).isTrue();

        assertThat(Task5.isValidCarNumber("123АВЕ777")).isFalse();
        assertThat(Task5.isValidCarNumber("А123ВГ77")).isFalse();
        assertThat(Task5.isValidCarNumber("А123ВЕ7777")).isFalse();
    }
}
