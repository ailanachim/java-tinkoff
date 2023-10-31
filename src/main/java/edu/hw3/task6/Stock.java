package edu.hw3.task6;

import org.jetbrains.annotations.NotNull;

public record Stock(int value) implements Comparable<Stock> {
    @Override
    public int compareTo(@NotNull Stock o) {
        return Integer.compare(value, o.value);
    }
}
