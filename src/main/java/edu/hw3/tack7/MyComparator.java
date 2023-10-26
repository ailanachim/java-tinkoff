package edu.hw3.tack7;

import java.util.Comparator;

public class MyComparator<T extends Comparable<T>> implements Comparator<T> {
    @Override
    public int compare(T o1, T o2) {
        if (o1 == null || o2 == null) {
            return nullCompare(o1, o2);
        }

        return o1.compareTo(o2);
    }

    private int nullCompare(T o1, T o2) {
        if (o1 == null && o2 == null) {
            return 0;
        } else if (o1 == null) {
            return -1;
        } else {
            return 1;
        }
    }
}
