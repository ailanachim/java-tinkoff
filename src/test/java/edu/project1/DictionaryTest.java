package edu.project1;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DictionaryTest {

    @Test
    void singleWordTest() {
        Dictionary dictionary = new Dictionary(List.of(new String[]{"abc"}));
        assertThat(dictionary.getWord()).isEqualTo("abc");
    }

    @Test
    void manyWordsTest() {
        var words = List.of(new String[]{"apple", "banana", "pear"});
        Dictionary dictionary = new Dictionary(words);

        assertThat(dictionary.getWord()).isIn(words);
        assertThat(dictionary.getWord()).isIn(words);
        assertThat(dictionary.getWord()).isIn(words);
        assertThat(dictionary.getWord()).isIn(words);
    }

    @Test
    void nullOrEmptyTest() {
        assertThatIllegalArgumentException().isThrownBy(() -> new Dictionary(null));
        assertThatIllegalArgumentException().isThrownBy(() -> new Dictionary(new ArrayList<>()));
    }
}
