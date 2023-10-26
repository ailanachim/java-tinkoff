package edu.hw3;

import java.util.Map;
import static java.util.Map.entry;

public class Task4 {

    private static final Map<Integer, String> INT_TO_ROMAN_DICT = Map.ofEntries(
        entry(1, "I"),
        entry(4, "IV"),
        entry(5, "V"),
        entry(9, "IX"),
        entry(10, "X"),
        entry(40, "XL"),
        entry(50, "L"),
        entry(90, "XC"),
        entry(100, "C"),
        entry(400, "CD"),
        entry(500, "D"),
        entry(900, "CM"),
        entry(1000, "M")
    );

    private static final int[] MAIN_NUMBERS = new int[] {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

    private Task4() {
    }

    public static String convertToRoman(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException("Number must be positive");
        }

        int x = number;
        StringBuilder result = new StringBuilder();
        while (x > 0) {
            for (int a : MAIN_NUMBERS) {
                if (x >= a) {
                    result.append(INT_TO_ROMAN_DICT.get(a));
                    x -= a;
                    break;
                }
            }
        }

        return result.toString();
    }
}
