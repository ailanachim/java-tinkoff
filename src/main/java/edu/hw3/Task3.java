package edu.hw3;

import java.util.Map;
import java.util.TreeMap;

public class Task3 {
    private Task3() {
    }

    public static <T> Map<T, Integer> freqDict(T[] objects) {
        if (objects == null) {
            throw new IllegalArgumentException();
        }

        Map<T, Integer> dict = new TreeMap<>();
        for (T obj : objects) {
            if (dict.containsKey(obj)) {
                dict.put(obj, dict.get(obj) + 1);
            } else {
                dict.put(obj, 1);
            }
        }

        return dict;
    }
}
