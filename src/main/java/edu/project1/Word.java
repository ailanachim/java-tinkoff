package edu.project1;

public class Word {

    private final String word;
    private final boolean[] guessed;
    private int mistakes;
    private static final int MAX_MISTAKES = 10;
    private static final int ALPHABET = 26;
    private final boolean[] used = new boolean[ALPHABET];

    public Word(String word) {
        this.word = word;
        guessed = new boolean[word.length()];
    }

    public GuessResult guessLetter(char letter) {
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

        if (letterExist) {
            if (isGuessed()) {
                return GuessResult.Win;
            }
            return GuessResult.Success;
        } else {
            if (mistakes > MAX_MISTAKES) {
                return GuessResult.Defeat;
            } else {
                return GuessResult.Fail;
            }
        }
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

    public int maxMistakes() {
        return MAX_MISTAKES;
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
