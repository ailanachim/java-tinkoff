package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task8 {

    private Task8() {
    }

    public static boolean lengthIsOdd(String string) {
        if (string == null) {
            return false;
        }

        Pattern pattern = Pattern.compile("[01](?:[01]{2})*");
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }

    public static boolean check2(String string) {
        if (string == null) {
            return false;
        }

        Pattern pattern = Pattern.compile("(0([01]{2})*)|(1(?:(?:[01]{2})*[01])?)");
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }

    public static boolean check3(String string) {
        if (string == null) {
            return false;
        }

        Pattern pattern = Pattern.compile("1*(01*01*0)*1*");
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }

    public static boolean check4(String string) {
        if (string == null) {
            return false;
        }

        Pattern pattern = Pattern.compile("(0[01]*)|(10[01]*)|1?|(110[01]*)|(111[01]+)");
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }

    public static boolean check5(String string) {
        if (string == null) {
            return false;
        }

        Pattern pattern = Pattern.compile("(?:1[01])*1?");
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }

    public static boolean check6(String string) {
        if (string == null) {
            return false;
        }

        Pattern pattern = Pattern.compile("(0{2,}1?)|(0+10+)|(10{2,})");
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }

    public static boolean check7(String string) {
        if (string == null) {
            return false;
        }

        Pattern pattern = Pattern.compile("0*(?:10+)*1?");
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }
}
