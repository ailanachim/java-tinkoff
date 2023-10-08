package edu.project1;

import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

public final class Main {

    static final int MAX_MISTAKES = 10;

    private Main() {
    }

    @SuppressWarnings("RegexpSinglelineJava")
    public static void main(String[] args) {
        Word word;
        try {
            word = new Word();
        } catch (FileNotFoundException exception) {
            System.out.println("Возникла ошибка, попробуйте позже");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Загадано слово: " + word.wordState());

        while (true) {
            System.out.println("Введите букву:");
            String input = scanner.nextLine();

            if (Objects.equals(input, "exit")) {
                System.out.println("Выход из игры. Загаданное слово было: " + word.getWord());
                break;
            }

            if (input.length() > 1 || input.charAt(0) < 'a' || input.charAt(0) > 'z') {
                continue;
            }

            if (word.guessLetter(input.charAt(0))) {
                System.out.println("Верно!");

                if (word.isGuessed()) {
                    System.out.println("Ура, Вы отгадали слово! Загаданное слово: " + word.getWord());
                    break;
                }

            } else {
                System.out.println("К сожалению, такой буквы нет");

                for (String line : hangman(word.getMistakes())) {
                    System.out.println(line);
                }

                if (word.getMistakes() > MAX_MISTAKES) {
                    System.out.println("Вы проиграли, загаданное слово было: " + word.getWord());
                    break;
                }

            }

            System.out.println("Текущее слово: " + word.wordState());
        }
    }

    @SuppressWarnings({"MagicNumber", "MultipleStringLiterals"})
    static String[] hangman(int mistakes) {
        return switch (mistakes) {
            case 1 -> new String[] {
                "|",
                "|",
                "|",
                "|",
                "|",
                "|"
            };
            case 2 -> new String[] {
                "|",
                "|",
                "|",
                "|",
                "|",
                "|_____"
            };
            case 3 -> new String[] {
                "____________",
                "|",
                "|",
                "|",
                "|",
                "|",
                "|_____"
            };
            case 4 -> new String[] {
                "____________",
                "| /",
                "|/",
                "|",
                "|",
                "|",
                "|_____"
            };
            case 5 -> new String[] {
                "____________",
                "| /    |",
                "|/",
                "|",
                "|",
                "|",
                "|_____"
            };
            case 6 -> new String[] {
                "____________",
                "| /    |",
                "|/    ()",
                "|",
                "|",
                "|",
                "|_____"
            };
            case 7 -> new String[] {
                "____________",
                "| /    |",
                "|/    ()",
                "|     {}",
                "|",
                "|",
                "|_____"
            };
            case 8 -> new String[] {
                "____________",
                "| /    |",
                "|/    ()",
                "|     {}\\",
                "|",
                "|",
                "|_____"
            };
            case 9 -> new String[] {
                "____________",
                "| /    |",
                "|/    ()",
                "|    /{}\\",
                "|",
                "|",
                "|_____"
            };
            case 10 -> new String[] {
                "____________",
                "| /    |",
                "|/    ()",
                "|    /{}\\",
                "|     /",
                "|",
                "|_____"
            };
            case 11 -> new String[] {
                "____________",
                "| /    |",
                "|/    ()",
                "|    /{}\\",
                "|     /\\",
                "|",
                "|_____"
            };
            default -> new String[] {};
        };
    }
}
