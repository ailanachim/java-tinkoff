package edu.hw8.task1;

import java.util.Map;

public class Dictionary {

    private final Map<String, String> map;

    public Dictionary(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.map = map;
    }

    public String getReply(String word) {
        return map.getOrDefault(word, map.values().stream().findAny().get());
    }
}
