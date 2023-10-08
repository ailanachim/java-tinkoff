package edu.hw1;

public class Task3 {

    private Task3() {
    }

    public static boolean isNestable(int[] a1, int[] a2) {
        if (a1.length == 0 || a2.length == 0) {
            return false;
        }

        return min(a1) > min(a2) && max(a1) < max(a2);
    }

    private static int min(int[] arr) {
        int min = arr[0];
        for (int a : arr) {
            if (a < min) {
                min = a;
            }
        }

        return min;
    }

    private static int max(int[] arr) {
        int max = arr[0];
        for (int a : arr) {
            if (a > max) {
                max = a;
            }
        }

        return max;
    }
}
