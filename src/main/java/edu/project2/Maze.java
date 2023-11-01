package edu.project2;

public class Maze {
    private final int height;
    private final int width;
    private final Cell[][] grid;
    private final Coordinate start;
    private final Coordinate end;

    public Maze(int height, int width, Cell[][] grid, Coordinate start, Coordinate end) {
        if (notValidArgs(height, width, grid, start, end)) {
            throw new IllegalArgumentException();
        }

        this.height = height;
        this.width = width;
        this.start = start;
        this.end = end;
        this.grid = grid;
    }

    public Maze(Maze maze) {
        if (maze == null) {
            throw new IllegalArgumentException();
        }

        this.height = maze.height;
        this.width = maze.width;
        this.start = maze.start;
        this.end = maze.end;
        this.grid = maze.grid;
    }

    private boolean notValidArgs(int height, int width, Cell[][] grid, Coordinate start, Coordinate end) {
        if (grid == null || grid.length != height) {
            return true;
        }

        for (Cell[] row : grid) {
            if (row.length != width) {
                return true;
            }
        }

        int x1 = start.row();
        int y1 = start.col();

        int x2 = end.row();
        int y2 = end.col();

        if (x1 < 0 || x1 >= height || y1 < 0 || y1 >= width) {
            return true;
        }

        if (x2 < 0 || x2 >= height || y2 < 0 || y2 >= width) {
            return true;
        }

        return grid[x1][y1] != Cell.PASSAGE || grid[x2][y2] != Cell.PASSAGE;
    }

    public int height() {
        return height;
    }

    public int width() {
        return width;
    }

    public Cell get(int x, int y) {
        return grid[x][y];
    }

    public Cell get(Coordinate coordinate) {
        return get(coordinate.row(), coordinate.col());
    }

    public Coordinate start() {
        return start;
    }

    public Coordinate end() {
        return end;
    }
}
