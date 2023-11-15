package edu.hw5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class Task2 {

    private static final int TARGET_DAY = 13;
    private static final DayOfWeek TARGET_DAY_OF_WEEK = DayOfWeek.FRIDAY;
    private static final int MONTHS = 12;

    private Task2() {
    }

    public static List<LocalDate> fridays13InYear(int year) {
        List<LocalDate> fridays13 = new ArrayList<>();

        for (int i = 1; i <= MONTHS; i++) {
            LocalDate date = LocalDate.of(year, i, TARGET_DAY);
            if (date.getDayOfWeek() == TARGET_DAY_OF_WEEK) {
                fridays13.add(date);
            }
        }

        return fridays13;
    }

    public static LocalDate nextFriday13(LocalDate current) {
        LocalDate date = current;
        if (date.getDayOfMonth() >= TARGET_DAY) {
            date = date.with(TemporalAdjusters.firstDayOfNextMonth());
        }

        date = date.withDayOfMonth(TARGET_DAY);

        while (date.getDayOfWeek() != TARGET_DAY_OF_WEEK) {
            date = date.plusMonths(1);
        }

        return date;
    }
}
