package edu.hw2.task3;

public class FaultyConnection implements Connection {

    private static final int FREQUENCY = 4;
    private int index = -1;

    @Override public void execute(String command) {
        index = (index + 1) % FREQUENCY;
        if (index == 0) {
            throw new ConnectionException();
        }
    }

    @Override public void close() {
    }
}
