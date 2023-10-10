package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static edu.hw1.Task1.minutesToSeconds;

public class Task1Test {

    @Test void incorrectFormatTest() {
        assertThat(minutesToSeconds(null)).isEqualTo(-1);
        assertThat(minutesToSeconds("")).isEqualTo(-1);
        assertThat(minutesToSeconds("blabla")).isEqualTo(-1);
        assertThat(minutesToSeconds("bla:bla")).isEqualTo(-1);
        assertThat(minutesToSeconds("12a:81")).isEqualTo(-1);
        assertThat(minutesToSeconds("123")).isEqualTo(-1);
        assertThat(minutesToSeconds("18.23")).isEqualTo(-1);
        assertThat(minutesToSeconds("17-30")).isEqualTo(-1);
        assertThat(minutesToSeconds("12:30:47")).isEqualTo(-1);
        assertThat(minutesToSeconds("12:60")).isEqualTo(-1);
    }

    @Test void correctFormatTest() {
        assertThat(minutesToSeconds("0:0")).isEqualTo(0);
        assertThat(minutesToSeconds("2:2")).isEqualTo(122);
        assertThat(minutesToSeconds("2:02")).isEqualTo(122);
        assertThat(minutesToSeconds("02:02")).isEqualTo(122);
        assertThat(minutesToSeconds("002:002")).isEqualTo(122);
        assertThat(minutesToSeconds("199999:59")).isEqualTo(11999999);
    }

}
