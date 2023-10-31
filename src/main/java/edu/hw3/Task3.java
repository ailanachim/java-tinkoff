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
            dict.put(obj, dict.getOrDefault(obj, 0) + 1);
        }

        return dict;
    }
}
