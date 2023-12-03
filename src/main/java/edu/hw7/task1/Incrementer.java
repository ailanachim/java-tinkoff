package edu.hw7.task1;

public class Incrementer extends Thread {

    private final Counter counter;
    private final int value;

    public Incrementer(Counter counter, int value) {
        this.counter = counter;
        this.value = value;
    }

    @Override
    public void run() {
        for (int i = 0; i < value; i++) {
            counter.increment();
        }
    }
}
