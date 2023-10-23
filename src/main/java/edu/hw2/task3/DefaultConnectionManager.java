package edu.hw2.task3;

public class DefaultConnectionManager implements ConnectionManager {

    private static final int FREQUENCY = 5;
    private int index = -1;

    @Override public Connection getConnection() {
        index = (index + 1) % FREQUENCY;
        if (index == 0) {
            return new FaultyConnection();
        }
        return new StableConnection();
    }
}
