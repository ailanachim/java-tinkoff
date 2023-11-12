package edu.hw5;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {

    @Test
    void test() {
        String s1 = "2022-03-12, 20:20 - 2022-03-12, 23:50";
        String s2 = "2022-04-01, 21:30 - 2022-04-02, 01:20";
        String s3 = "2022-04-02, 12:20 - 2022-04-02, 12:20";
        String s4 = "2022-04-07, 09:00 - 2022-04-10, 09:00";
        String s5 = "2022-04-21, 11:30 - 2022-04-21, 11:40";

        assertThat(Task1.averageTimeLength(new String[]{s1, s2, s3, s4, s5})).isEqualTo(954);
    }
}
