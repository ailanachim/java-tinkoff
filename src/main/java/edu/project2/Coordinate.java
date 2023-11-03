package edu.project2;

public record Coordinate(int row, int col) {

    public Coordinate {
        if (row < 0 || col < 0) {
            throw new IllegalArgumentException();
        }
    }

    public boolean isNear(Coordinate other) {
        return ((row() - other.row()) * (row() - other.row())
            + (col() - other.col()) * (col() - other.col())) == 1;
    }
}
