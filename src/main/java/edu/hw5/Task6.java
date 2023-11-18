package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task6 {

    private Task6() {
    }

    public static boolean isSubsequence(String s, String text) {
        if (s == null || text == null) {
            return false;
        }

        StringBuilder stringBuilder = new StringBuilder();

        final String any = ".*";
        stringBuilder.append(any);

        for (char ch : s.toCharArray()) {
            stringBuilder.append(ch);
            stringBuilder.append(any);
        }

        Pattern pattern = Pattern.compile(stringBuilder.toString());
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }
}
