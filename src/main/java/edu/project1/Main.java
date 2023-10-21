package edu.project1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class Main {

    private Main() {
    }

    public static void main(String[] args) throws FileNotFoundException {
        List<String> words = new ArrayList<>();
        Scanner scanner = new Scanner(new File("src/main/java/edu/project1/dictionary.txt"));
        while (scanner.hasNext()) {
            words.add(scanner.next());
        }
        scanner.close();

        Dictionary dictionary = new Dictionary(words);
        HangmanGame game = new HangmanGame(dictionary.getWord());
        game.run();
    }

}
