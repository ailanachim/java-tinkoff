package edu.hw2.task3;

public class ConnectionException extends RuntimeException {
    public ConnectionException(Exception exception) {
        super(exception);
    }

    public ConnectionException() {
    }
}
