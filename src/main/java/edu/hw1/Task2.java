package edu.hw1;

public class Task2 {

    private Task2() {
    }

    public static int countDigits(int number) {
        int x = number;
        int count = 0;
        final int base = 10;
        do {
            x /= base;
            count++;
        } while (x != 0);

        return count;
    }
}
