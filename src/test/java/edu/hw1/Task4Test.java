package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static edu.hw1.Task4.fixString;

public class Task4Test {

    @Test void fixStringTest() {
        assertThat(fixString("")).isEqualTo("");
        assertThat(fixString("123456")).isEqualTo("214365");
        assertThat(fixString("1234567")).isEqualTo("2143657");
        assertThat(fixString("hTsii  s aimex dpus rtni.g")).isEqualTo("This is a mixed up string.");
        assertThat(fixString("badce")).isEqualTo("abcde");
    }

}
