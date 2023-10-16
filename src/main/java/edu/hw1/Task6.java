package edu.hw1;

import java.util.Arrays;

public class Task6 {

    private Task6() {
    }

    public static int countK(int number) {
        if (number == kaprekar(number)) {
            return 0;
        }
        return countK(kaprekar(number)) + 1;
    }

    private static int kaprekar(int number) {
        final int countDigits = 4;
        final int base = 10;
        int x = number;

        int[] digits = new int[countDigits];
        for (int i = 0; i < countDigits; i++) {
            digits[i] = x % base;
            x /= base;
        }

        Arrays.sort(digits);
        int min = 0;
        for (int i = 0; i < countDigits; i++) {
            min = min * base + digits[i];
        }

        int max = 0;
        for (int i = countDigits - 1; i >= 0; i--) {
            max = max * base + digits[i];
        }

        return max - min;
    }

}
