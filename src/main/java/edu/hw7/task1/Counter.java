package edu.hw7.task1;

import java.util.concurrent.atomic.AtomicInteger;

public class Counter {

    private final AtomicInteger value;

    public Counter(int value) {
        this.value = new AtomicInteger(value);
    }

    public void increment() {
        value.getAndIncrement();
    }

    public int value() {
        return value.get();
    }
}
