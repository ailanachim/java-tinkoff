package edu.project1;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class WordTest {

    @Test
    void successGuessTest() {
        Word word = new Word("people");

        assertThat(word.guessLetter('o')).isInstanceOf(GuessResult.SuccessGuess.class);
        assertThat(word.wordState()).isEqualTo("**o***");

        assertThat(word.guessLetter('e')).isInstanceOf(GuessResult.SuccessGuess.class);
        assertThat(word.wordState()).isEqualTo("*eo**e");

        assertThat(word.guessLetter('p')).isInstanceOf(GuessResult.SuccessGuess.class);
        assertThat(word.guessLetter('l')).isInstanceOf(GuessResult.Win.class);
    }

    @Test
    void failedGuessTest() {
        Word word = new Word("ocean");

        assertThat(word.guessLetter('b')).isInstanceOf(GuessResult.FailedGuess.class);
        assertThat(word.getMistakes()).isEqualTo(1);

        assertThat(word.guessLetter('d')).isInstanceOf(GuessResult.FailedGuess.class);
        assertThat(word.getMistakes()).isEqualTo(2);

        assertThat(word.guessLetter('d')).isInstanceOf(GuessResult.FailedGuess.class);
        assertThat(word.getMistakes()).isEqualTo(2);

        assertThat(word.guessLetter('f')).isInstanceOf(GuessResult.FailedGuess.class);
        assertThat(word.guessLetter('g')).isInstanceOf(GuessResult.FailedGuess.class);
        assertThat(word.guessLetter('h')).isInstanceOf(GuessResult.FailedGuess.class);
        assertThat(word.guessLetter('i')).isInstanceOf(GuessResult.FailedGuess.class);
        assertThat(word.guessLetter('j')).isInstanceOf(GuessResult.FailedGuess.class);
        assertThat(word.guessLetter('k')).isInstanceOf(GuessResult.FailedGuess.class);
        assertThat(word.guessLetter('l')).isInstanceOf(GuessResult.FailedGuess.class);
        assertThat(word.guessLetter('m')).isInstanceOf(GuessResult.FailedGuess.class);

        assertThat(word.getMistakes()).isEqualTo(word.maxMistakes());
        assertThat(word.isFinalState()).isFalse();

        assertThat(word.guessLetter('p')).isInstanceOf(GuessResult.Defeat.class);
        assertThat(word.isFinalState()).isTrue();
    }

    @Test
    void invalidArgumentTest() {
        assertThatIllegalArgumentException().isThrownBy(() -> new Word(null));
        assertThatIllegalArgumentException().isThrownBy(() -> new Word(""));
        assertThatIllegalArgumentException().isThrownBy(() -> new Word("Bla-bla bla"));
    }
}
