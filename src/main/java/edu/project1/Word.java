package edu.project1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Word {

    private final String word;
    private final boolean[] guessed;
    private int mistakes;
    final int alphabet = 26;
    private final boolean[] used;

    public Word() throws FileNotFoundException {
        ArrayList<String> dictionary = new ArrayList<>();
        Scanner scanner = new Scanner(new File("src/main/java/edu/project1/dictionary.txt"));
        while (scanner.hasNext()) {
            dictionary.add(scanner.next());
        }
        scanner.close();

        int index = (int) (Math.random() * dictionary.size());
        word = dictionary.get(index);
        guessed = new boolean[word.length()];
        used = new boolean[alphabet];
    }

    public boolean guessLetter(char letter) {
        boolean letterExist = false;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letter) {
                guessed[i] = true;
                letterExist = true;
            }
        }

        if (!letterExist && !used[letter - 'a']) {
            mistakes++;
        }
        used[letter - 'a'] = true;
        return letterExist;
    }

    public String wordState() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            if (guessed[i]) {
                stringBuilder.append(word.charAt(i));
            } else {
                stringBuilder.append('*');
            }
        }

        return stringBuilder.toString();
    }

    public String getWord() {
        return word;
    }

    public int getMistakes() {
        return mistakes;
    }

    public boolean isGuessed() {
        for (boolean b : guessed) {
            if (!b) {
                return false;
            }
        }

        return true;
    }
}
