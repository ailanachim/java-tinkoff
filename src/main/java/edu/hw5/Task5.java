package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task5 {

    private Task5() {
    }

    public static boolean isValidCarNumber(String string) {
        if (string == null) {
            return false;
        }

        String letters = "АВЕКМНОРСТУХ";

        Pattern pattern = Pattern.compile(String.format("[%s]\\d{3}[%s][%s]\\d{2,3}", letters, letters, letters));
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }
}
