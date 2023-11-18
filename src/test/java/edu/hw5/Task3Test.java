package edu.hw5;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {

    @Test
    void parseDateTest() {
        Optional<LocalDate> date = Optional.of(LocalDate.of(2020, 2, 3));

        assertThat(Task3.parseDate("2020-02-03")).isEqualTo(date);
        assertThat(Task3.parseDate("2020-2-3")).isEqualTo(date);
        assertThat(Task3.parseDate("03/02/2020")).isEqualTo(date);
        assertThat(Task3.parseDate("3/2/2020")).isEqualTo(date);
        assertThat(Task3.parseDate("3/2/20")).isEqualTo(date);

        assertThat(Task3.parseDate("today")).isEqualTo(Optional.of(LocalDate.now()));
        assertThat(Task3.parseDate("yesterday"))
            .isEqualTo(Optional.of(LocalDate.now().minusDays(1)));
        assertThat(Task3.parseDate("tomorrow"))
            .isEqualTo(Optional.of(LocalDate.now().plusDays(1)));

        assertThat(Task3.parseDate("1 day ago"))
            .isEqualTo(Optional.of(LocalDate.now().minusDays(1)));
        assertThat(Task3.parseDate("2234 days ago"))
            .isEqualTo(Optional.of(LocalDate.now().minusDays(2234)));

        assertThat(Task3.parseDate(null)).isEqualTo(Optional.empty());
        assertThat(Task3.parseDate("")).isEqualTo(Optional.empty());
        assertThat(Task3.parseDate("bla-bla")).isEqualTo(Optional.empty());
    }
}
