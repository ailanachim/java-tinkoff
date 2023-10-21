package edu.project1;

import java.util.Objects;
import java.util.Scanner;

public class HangmanGame {
    Word word;
    private static final String EXIT_COMMAND = "exit";

    public HangmanGame(String word) {
        this.word = new Word(word);
    }

    @SuppressWarnings("RegexpSinglelineJava")
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Загадано слово: " + word.wordState());

        while (!word.isFinalState()) {
            System.out.println("Введите букву:");
            String input = scanner.nextLine().strip().toLowerCase();

            if (Objects.equals(input, EXIT_COMMAND)) {
                System.out.println("Выход из игры. Загаданное слово было: " + word.getWord());
                break;
            }

            if (input.length() != 1 || input.charAt(0) < 'a' || input.charAt(0) > 'z') {
                System.out.println("Введите строчную латинскую букву:");
                continue;
            }

            GuessResult guessResult = word.guessLetter(input.charAt(0));
            System.out.println(guessResult.message());
        }
    }
}
