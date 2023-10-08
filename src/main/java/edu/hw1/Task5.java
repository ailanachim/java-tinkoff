package edu.hw1;

class Task5 {

    private static final int BASE = 10;

    private Task5() {
    }

    public static boolean isPalindromeDescendant(int number) {
        if (descendant(number) < BASE) {
            return isPalindrome(number);
        } else if (isPalindrome(number)) {
            return true;
        }

        return isPalindromeDescendant(descendant(number));
    }

    private static int descendant(int number) {
        int x = number;
        int descendant = 0;
        while (x != 0) {
            int a = x % BASE;
            x /= BASE;
            int b = x % BASE;
            x /= BASE;
            int c = a + b;
            if (c < BASE) {
                descendant = descendant * BASE + c;
            } else {
                descendant = descendant * BASE * BASE + a + b;
            }
        }

        return descendant;
    }

    private static boolean isPalindrome(int number) {
        String string = Integer.toString(Math.abs(number));
        int n = string.length();
        for (int i = 0; i < n; i++) {
            if (string.charAt(i) != string.charAt(n - i - 1)) {
                return false;
            }
        }

        return true;
    }
}
