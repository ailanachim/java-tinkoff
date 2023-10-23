package edu.hw2.task3;

public class ExceededAttemptsNumberException extends RuntimeException {

    public ExceededAttemptsNumberException(Exception cause) {
        super(cause);
    }
}
