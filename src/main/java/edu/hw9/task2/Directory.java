package edu.hw9.task2;

public record Directory(String name, File[] files, Directory[] directories) {

    public Directory {
        if (name == null || files == null || directories == null) {
            throw new IllegalArgumentException();
        }
    }
}
