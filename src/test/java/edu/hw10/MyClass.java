package edu.hw10;

import edu.hw10.task1.Max;
import edu.hw10.task1.Min;
import edu.hw10.task1.NotNull;
import java.util.ArrayList;

public class MyClass {

    private String name;
    private int value;

    public MyClass(@NotNull String name, @Min(0) @Max(10) int value, ArrayList<Integer> list, int[] arr) {
        this.name = name;
        this.value = value;
    }

    public static MyClass create(
        @NotNull String name,
        @Min(0) @Max(10) int value,
        ArrayList<Integer> list,
        int[] arr
    ) {
        return new MyClass(name, value, list, arr);
    }

    public int value() {
        return value;
    }

    public String name() {
        return name;
    }
}
