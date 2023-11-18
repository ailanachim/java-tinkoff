package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task7 {

    private Task7() {
    }

    public static boolean notLess3CharsAndThirdIs0(String string) {
        if (string == null) {
            return false;
        }

        Pattern pattern = Pattern.compile("[01]{2}0[01]*");
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }

    public static boolean startsAndEndsWithSame(String string) {
        if (string == null) {
            return false;
        }

        Pattern pattern = Pattern.compile("(0[01]*0)|(1[01]*1)|[01]");
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }

    public static boolean lengthBetween1And3(String string) {
        if (string == null) {
            return false;
        }

        Pattern pattern = Pattern.compile("[01][01]?[01]?");
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }
}
