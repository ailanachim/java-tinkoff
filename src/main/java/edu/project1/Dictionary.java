package edu.project1;

import java.util.List;

public class Dictionary {

    private final List<String> dictionary;

    public Dictionary(List<String> words) {
        if (words == null || words.isEmpty()) {
            throw new IllegalArgumentException();
        }
        dictionary = words;
    }

    public String getWord() {
        return dictionary.get((int) (Math.random() * dictionary.size()));
    }
}
