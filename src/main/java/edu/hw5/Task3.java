package edu.hw5;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task3 {

    private Task3() {
    }

    @SuppressWarnings("ReturnCount")
    public static Optional<LocalDate> parseDate(String string) {
        if (string == null) {
            return Optional.empty();
        }

        Optional<LocalDate> res = parseByFormat1(string);
        if (res.isPresent()) {
            return res;
        }

        res = parseByFormat2(string);
        if (res.isPresent()) {
            return res;
        }

        res = parseByFormat3(string);
        if (res.isPresent()) {
            return res;
        }

        return parseByFormat4(string);
    }

    @SuppressWarnings("MagicNumber")
    private static Optional<LocalDate> parseByFormat1(String string) {
        String format = "(\\d{4})-(0?[1-9]|1[0-2])-((?:0?[1-9]|[12]\\d)|3[01])"; // YYYY-MM-DD

        Pattern pattern = Pattern.compile(format);
        Matcher matcher = pattern.matcher(string);

        if (matcher.matches()) {
            MatchResult result = matcher.toMatchResult();
            int year = Integer.parseInt(result.group(1));
            int month = Integer.parseInt(result.group(2));
            int day = Integer.parseInt(result.group(3));
            return Optional.of(LocalDate.of(year, month, day));
        }

        return Optional.empty();
    }

    @SuppressWarnings("MagicNumber")
    private static Optional<LocalDate> parseByFormat2(String string) {
        String format =
            "((?:0?[1-9]|[12]\\d)|3[01])/(0?[1-9]|1[0-2])/(\\d{2}(?:\\d{2})?)"; // DD/MM/YY

        Pattern pattern = Pattern.compile(format);
        Matcher matcher = pattern.matcher(string);

        if (matcher.matches()) {
            MatchResult result = matcher.toMatchResult();
            int day = Integer.parseInt(result.group(1));
            int month = Integer.parseInt(result.group(2));
            int year = Integer.parseInt(result.group(3));
            if (year < 100) {
                year += 2000;
            }
            return Optional.of(LocalDate.of(year, month, day));
        }

        return Optional.empty();
    }

    private static Optional<LocalDate> parseByFormat3(String string) {
        String format = "(\\d+) days? ago";

        Pattern pattern = Pattern.compile(format);
        Matcher matcher = pattern.matcher(string);
        if (matcher.matches()) {
            LocalDate date = LocalDate.now();
            int daysOffset = Integer.parseInt(matcher.group(1));
            return Optional.of(date.minusDays(daysOffset));
        }

        return Optional.empty();
    }

    private static Optional<LocalDate> parseByFormat4(String string) {
        if (string.equals("tomorrow")) {
            return Optional.of(LocalDate.now().plusDays(1));
        }

        if (string.equals("today")) {
            return Optional.of(LocalDate.now());
        }

        if (string.equals("yesterday")) {
            return Optional.of(LocalDate.now().minusDays(1));
        }

        return Optional.empty();
    }
}
