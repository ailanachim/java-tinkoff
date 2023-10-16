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

        while (true) {
            System.out.println("Введите букву:");
            String input = scanner.nextLine();

            if (Objects.equals(input, EXIT_COMMAND)) {
                System.out.println("Выход из игры. Загаданное слово было: " + word.getWord());
                break;
            }

            if (input.length() != 1 || input.charAt(0) < 'a' || input.charAt(0) > 'z') {
                System.out.println("Введите строчную латинскую букву:");
                continue;
            }

            GuessResult guessResult = word.guessLetter(input.charAt(0));
            if (guessResult == GuessResult.Win) {
                System.out.println("Ура, Вы отгадали слово! Загаданное слово: " + word.getWord());
                break;
            } else if (guessResult == GuessResult.Defeat) {
                System.out.println("Вы проиграли, загаданное слово было: " + word.getWord());
                break;
            } else if (guessResult == GuessResult.Success) {
                System.out.println("Верно! Текущее слово:" + word.wordState());
            } else if (guessResult == GuessResult.Fail) {
                System.out.printf("К сожалению, такой буквы нет. Неудачных попыток %d из %d\n",
                        word.getMistakes(), word.maxMistakes());
                System.out.println("Текущее слово:" + word.wordState());
            }
        }
    }
}
