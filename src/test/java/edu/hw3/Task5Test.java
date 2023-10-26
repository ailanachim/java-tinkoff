package edu.hw3;

import org.junit.jupiter.api.Test;
import static edu.hw3.Task5.parseContacts;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task5Test {

    @Test
    void test() {
        assertThat(parseContacts(new String[] {"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"}, "ASC"))
            .isEqualTo(new String[] {"Thomas Aquinas", "Rene Descartes", "David Hume", "John Locke"});

        assertThat(parseContacts(new String[] {"Paul Erdos", "Leonhard Euler", "Carl Gauss"}, "DESC"))
            .isEqualTo(new String[] {"Carl Gauss", "Leonhard Euler", "Paul Erdos"});

        assertThat(parseContacts(new String[] {"Paul", "Leonhard Euler", "Carl Gauss"}, "DESC"))
            .isEqualTo(new String[] {"Paul", "Carl Gauss", "Leonhard Euler"});

        assertThat(parseContacts(new String[] {"John Locke", "Thomas Aquinas", "David Hume"}, "blabla"))
            .isEqualTo(new String[] {"John Locke", "Thomas Aquinas", "David Hume"});

        assertThat(parseContacts(new String[] {}, "ASC")).isEqualTo(new String[] {});
        assertThat(parseContacts(null, "DESC")).isEqualTo(new String[] {});
    }
}
