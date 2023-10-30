package edu.hw3;

import java.util.ArrayList;
import java.util.List;

public class Task2 {
    final static char OPEN_BRACKET = '(';
    final static char CLOSE_BRACKET = ')';

    private Task2() {
    }

    public static String[] clusterize(String brackets) {
        if (notValid(brackets)) {
            throw new IllegalArgumentException();
        }

        List<String> clusters = new ArrayList<>();

        int k = 0;
        int start = 0;

        for (int i = 0; i < brackets.length(); i++) {
            char c = brackets.charAt(i);

            if (c == OPEN_BRACKET) {
                k += 1;
            } else {
                k -= 1;
            }

            if (k == 0) {
                clusters.add(brackets.substring(start, i + 1));
                start = i + 1;
            }
        }

        return clusters.toArray(String[]::new);
    }

    private static boolean notValid(String brackets) {
        if (brackets == null) {
            return true;
        }

        int k = 0;
        for (char c : brackets.toCharArray()) {
            if (c == OPEN_BRACKET) {
                k += 1;
            } else if (c == CLOSE_BRACKET) {
                k -= 1;
                if (k < 0) {
                    return true;
                }
            } else {
                return true;
            }
        }

        return k != 0;
    }
}
