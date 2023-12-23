package edu.hw10;

import edu.hw10.task1.Max;
import edu.hw10.task1.Min;
import edu.hw10.task1.NotNull;

public record Person(long id, @NotNull String name, @Min(10) int age, @Max(2) float height, boolean flag) {

    public static Person create(
        long id,
        @NotNull String name,
        @Min(10) int age,
        @Max(2) float height,
        boolean flag
    ) {
        return new Person(id, name, age, height, flag);
    }
}
