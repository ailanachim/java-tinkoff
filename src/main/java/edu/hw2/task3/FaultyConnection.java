package edu.hw2.task3;

public class FaultyConnection implements Connection {

    private final static double FAULT_PROBABILITY = 0.3;

    @Override public void execute(String command) {
        if (Math.random() <= FAULT_PROBABILITY) {
            throw new ConnectionException();
        }
    }

    @Override public void close() {
    }
}
