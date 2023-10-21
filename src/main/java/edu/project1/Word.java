package edu.project1;

public class Word {

    private final String word;
    private int mistakes;
    private static final int MAX_MISTAKES = 10;
    private static final int ALPHABET = 26;
    private final boolean[] used = new boolean[ALPHABET];

    public Word(String word) {
        if (!validWord(word)) {
            throw new IllegalArgumentException("word must contain lowercase latin characters");
        }
        this.word = word;
    }

    public GuessResult guessLetter(char letter) {
        boolean letterExist = word.contains(Character.toString(letter));
        if (!letterExist && !used[letter - 'a']) {
            mistakes++;
        }
        used[letter - 'a'] = true;

        if (letterExist) {
            if (isGuessed()) {
                return new GuessResult.Win(word);
            }
            return new GuessResult.SuccessGuess(wordState());
        } else {
            if (mistakes > MAX_MISTAKES) {
                return new GuessResult.Defeat(word);
            } else {
                return new GuessResult.FailedGuess(wordState(), mistakes, MAX_MISTAKES);
            }
        }
    }

    public String wordState() {
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : word.toCharArray()) {
            if (used[c - 'a']) {
                stringBuilder.append(c);
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
        for (char c : word.toCharArray()) {
            if (!used[c - 'a']) {
                return false;
            }
        }

        return true;
    }

    public boolean isFinalState() {
        return isGuessed() || mistakes > MAX_MISTAKES;
    }

    private boolean validWord(String word) {
        if (word == null || word.isEmpty()) {
            return false;
        }
        for (char c : word.toCharArray()) {
            if (c > 'z' || c < 'a') {
                return false;
            }
        }

        return true;
    }
}
