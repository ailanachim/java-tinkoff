package edu.hw5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class Task2 {

    private Task2() {
    }

    public static List<LocalDate> fridays13InYear(int year) {
        List<LocalDate> fridays13 = new ArrayList<>();

        final int months = 12;
        final int targetDay = 13;

        for (int i = 1; i <= months; i++) {
            LocalDate date = LocalDate.of(year, i, targetDay);
            if (date.getDayOfWeek() == DayOfWeek.FRIDAY) {
                fridays13.add(date);
            }
        }

        return fridays13;
    }

    public static LocalDate nextFriday13(LocalDate current) {
        final int targetDay = 13;
        LocalDate date = current;
        if (date.getDayOfMonth() >= targetDay) {
            date = date.with(TemporalAdjusters.firstDayOfNextMonth());
        }

        date = date.withDayOfMonth(targetDay);

        while (date.getDayOfWeek() != DayOfWeek.FRIDAY) {
            date = date.plusMonths(1);
        }

        return date;
    }
}
