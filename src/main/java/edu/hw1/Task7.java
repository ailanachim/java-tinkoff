package edu.hw1;

public class Task7 {

    private Task7() {
    }

    public static int rotateLeft(int number, int shift) {
        if (number == 0) {
            return number;
        }

        int n = number;
        int x = 1;
        while (x << 1 <= n) {
            x = x << 1;
        }

        for (int i = 0; i < shift; i++) {
            if (n >= x) {
                n = ((n - x) << 1) + 1;
            } else {
                n = n << 1;
            }
        }

        return n;
    }

    public static int rotateRight(int number, int shift) {
        if (number == 0) {
            return number;
        }

        int n = number;
        int x = 1;
        while (x << 1 <= n) {
            x = x << 1;
        }

        for (int i = 0; i < shift; i++) {
            if (n % 2 == 1) {
                n = (n >> 1) + x;
            } else {
                n = n >> 1;
            }
        }

        return n;
    }

}
