package edu.project1;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class HangmanTest {

    @Test
    void successGuess() {
        Word word = new Word("people");

        assertThat(word.guessLetter('o')).isEqualTo(GuessResult.Success);
        assertThat(word.guessLetter('e')).isEqualTo(GuessResult.Success);
        assertThat(word.guessLetter('p')).isEqualTo(GuessResult.Success);
        assertThat(word.guessLetter('l')).isEqualTo(GuessResult.Win);
    }

    @Test
    void failedGuess() {
        Word word = new Word("ocean");

        assertThat(word.guessLetter('b')).isEqualTo(GuessResult.Fail);
        assertThat(word.getMistakes()).isEqualTo(1);

        assertThat(word.guessLetter('d')).isEqualTo(GuessResult.Fail);
        assertThat(word.getMistakes()).isEqualTo(2);

        assertThat(word.guessLetter('d')).isEqualTo(GuessResult.Fail);
        assertThat(word.getMistakes()).isEqualTo(2);

        assertThat(word.guessLetter('f')).isEqualTo(GuessResult.Fail);
        assertThat(word.guessLetter('g')).isEqualTo(GuessResult.Fail);
        assertThat(word.guessLetter('h')).isEqualTo(GuessResult.Fail);
        assertThat(word.guessLetter('i')).isEqualTo(GuessResult.Fail);
        assertThat(word.guessLetter('j')).isEqualTo(GuessResult.Fail);
        assertThat(word.guessLetter('k')).isEqualTo(GuessResult.Fail);
        assertThat(word.guessLetter('l')).isEqualTo(GuessResult.Fail);
        assertThat(word.guessLetter('m')).isEqualTo(GuessResult.Fail);

        assertThat(word.getMistakes()).isEqualTo(word.maxMistakes());
        assertThat(word.guessLetter('p')).isEqualTo(GuessResult.Defeat);
    }
}
