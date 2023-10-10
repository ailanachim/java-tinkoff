package edu.hw1;

public class Task4 {

    private Task4() {
    }

    public static String fixString(String string) {
        if (string == null) {
            return null;
        }

        char[] chars = string.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (i % 2 == 1) {
                char c = chars[i];
                chars[i] = chars[i - 1];
                chars[i - 1] = c;
            }
        }

        return new String(chars);
    }
}
