package edu.hw5;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {

    @Test
    void fridays13InYearTest() {
        assertThat(Task2.fridays13InYear(1925)).isEqualTo(List.of(
            LocalDate.of(1925, 2, 13),
            LocalDate.of(1925, 3, 13),
            LocalDate.of(1925, 11, 13)
        ));

        assertThat(Task2.fridays13InYear(2024)).isEqualTo(List.of(
            LocalDate.of(2024, 9, 13),
            LocalDate.of(2024, 12, 13)
        ));

        assertThat(Task2.fridays13InYear(1924)).isEqualTo(List.of(
            LocalDate.of(1924, 6, 13)
        ));
    }

    @Test
    void nextFriday13Test() {
        assertThat(Task2.nextFriday13(LocalDate.of(1925, 2, 1)))
            .isEqualTo(LocalDate.of(1925, 2, 13));

        assertThat(Task2.nextFriday13(LocalDate.of(1925, 2, 13)))
            .isEqualTo(LocalDate.of(1925, 3, 13));

        assertThat(Task2.nextFriday13(LocalDate.of(1925, 2, 24)))
            .isEqualTo(LocalDate.of(1925, 3, 13));

        assertThat(Task2.nextFriday13(LocalDate.of(1924, 8, 1)))
            .isEqualTo(LocalDate.of(1925, 2, 13));
    }
}
