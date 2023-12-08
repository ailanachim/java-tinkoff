package edu.hw9.task2;

public record File(String name, int size) {
    public File {
        if (name == null || size < 0) {
            throw new IllegalArgumentException();
        }
    }
}
