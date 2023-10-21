package edu.project1;

import org.junit.jupiter.api.Test;

import java.io.StringBufferInputStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class HangmanTest {
    @Test
    void winGameTest() {
        String word = "abc";
        HangmanGame game = new HangmanGame(word);

        String input = "a\nb\nc";
        StringBufferInputStream inputStream = new StringBufferInputStream(input);
        System.setIn(inputStream);

        game.run();
    }

    @Test
    void defeatGameTest() {
        String word = "a";
        HangmanGame game = new HangmanGame(word);

        StringBuilder stringBuilder = new StringBuilder();
        for (char c = 'b'; c <= 'z'; c++) {
            stringBuilder.append(c);
            stringBuilder.append('\n');
        }

        String input = stringBuilder.toString();
        StringBufferInputStream inputStream = new StringBufferInputStream(input);
        System.setIn(inputStream);

        game.run();
    }

    @Test
    void exitTest() {
        String word = "a";
        HangmanGame game = new HangmanGame(word);

        String input = "exit";
        StringBufferInputStream inputStream = new StringBufferInputStream(input);
        System.setIn(inputStream);

        game.run();
    }
}
