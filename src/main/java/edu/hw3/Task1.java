package edu.hw3;

public class Task1 {

    private Task1() {
    }

    public static String atbash(String input) {
        if (input == null) {
            throw new IllegalArgumentException();
        }

        char[] chars = input.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] >= 'a' && chars[i] <= 'z') {
                chars[i] = (char) ('z' - chars[i] + 'a');
            } else if (chars[i] >= 'A' && chars[i] <= 'Z') {
                chars[i] = (char) ('Z' - chars[i] + 'A');
            }
        }

        return new String(chars);
    }
}
