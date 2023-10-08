package edu.project1;

import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class HangmanTest {

    @Test
    void successGuess() throws FileNotFoundException {
        Word word = new Word();

        for (char c : word.getWord().toCharArray()) {
            assertThat(word.guessLetter(c)).isTrue();
        }

        assertThat(word.isGuessed()).isTrue();
    }

    @Test
    void failedGuess() throws FileNotFoundException {
        Word word = new Word();

        char c = 'a';
        while (word.getWord().contains(Character.toString(c))) {
            c++;
        }

        assertThat(word.guessLetter(c)).isFalse();
        assertThat(word.isGuessed()).isFalse();
        assertThat(word.getMistakes()).isEqualTo(1);
        word.guessLetter(c);
        assertThat(word.getMistakes()).isEqualTo(1);

        do {
            c++;
        } while (word.getWord().contains(Character.toString(c)));

        assertThat(word.guessLetter(c)).isFalse();
        assertThat(word.getMistakes()).isEqualTo(2);
    }
}
