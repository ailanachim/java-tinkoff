package edu.hw5;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task1 {

    private Task1() {
    }

    /**
     * returns average length of time intervals in minutes
     *
     * @param intervals array of string with pattern "YYYY-MM-DD, hh:mm - YYYY-MM-DD, hh:mm"
     */
    public static long averageTimeLength(String[] intervals) {
        if (intervals == null || intervals.length == 0) {
            throw new IllegalArgumentException("String array is null or empty");
        }

        long sum = 0;
        for (String interval : intervals) {
            sum += parse(interval).length();
        }

        return sum / intervals.length;
    }

    @SuppressWarnings("MagicNumber")
    private static Interval parse(String interval) {
        String year = "(\\d{4})";
        String month = "((?:0[1-9])|(?:1[0-2]))";
        String day = "((?:(?:0[1-9])|(?:[12]\\d))|(?:3[01]))";
        String hour = "((?:[01]\\d)|(?:2[0-3]))";
        String minute = "([0-5]\\d)";

        String date = String.format("%s-%s-%s,\\s?%s:%s", year, month, day, hour, minute);
        String regexp = date + "\\s*-\\s*" + date;

        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(interval);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid date format");
        }

        MatchResult result = matcher.toMatchResult();

        int year1 = Integer.parseInt(result.group(1));
        int month1 = Integer.parseInt(result.group(2));
        int day1 = Integer.parseInt(result.group(3));
        int hour1 = Integer.parseInt(result.group(4));
        int min1 = Integer.parseInt(result.group(5));

        int year2 = Integer.parseInt(result.group(6));
        int month2 = Integer.parseInt(result.group(7));
        int day2 = Integer.parseInt(result.group(8));
        int hour2 = Integer.parseInt(result.group(9));
        int min2 = Integer.parseInt(result.group(10));

        LocalDateTime start = LocalDateTime.of(year1, month1, day1, hour1, min1);
        LocalDateTime end = LocalDateTime.of(year2, month2, day2, hour2, min2);

        return new Interval(start, end);
    }

    record Interval(LocalDateTime start, LocalDateTime end) {
        private long length() {
            return Duration.between(start, end).toMinutes();
        }
    }
}
