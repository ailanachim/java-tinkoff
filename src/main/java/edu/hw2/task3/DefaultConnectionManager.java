package edu.hw2.task3;

public class DefaultConnectionManager implements ConnectionManager {

    private final static double FAULT_PROBABILITY = 0.2;

    @Override public Connection getConnection() {
        if (Math.random() <= FAULT_PROBABILITY) {
            return new FaultyConnection();
        }
        return new StableConnection();
    }
}
