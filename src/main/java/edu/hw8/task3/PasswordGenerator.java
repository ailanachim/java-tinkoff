package edu.hw8.task3;

public class PasswordGenerator {

    private final static String ALPHABET = "abcdefghijklmnopqrstuvwxyz0123456789";
    private final static int CAPACITY = 4;

    private PasswordGenerator() {
    }

    public static String getPassword(int number) {
        int n = number;
        StringBuilder stringBuilder = new StringBuilder(CAPACITY);
        while (n > 0) {
            stringBuilder.append(ALPHABET.charAt(((n - 1) % ALPHABET.length())));
            n = (n - 1) / ALPHABET.length();
        }

        return stringBuilder.toString();
    }
}
