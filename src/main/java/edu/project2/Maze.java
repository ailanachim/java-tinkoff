package edu.project2;

public class Maze {
    private final Cell[][] grid;
    private final Coordinate start;
    private final Coordinate end;

    public Maze(Cell[][] grid, Coordinate start, Coordinate end) {
        if (notValidArgs(grid, start, end)) {
            throw new IllegalArgumentException();
        }

        this.start = start;
        this.end = end;
        this.grid = grid;
    }

    public Maze(Maze maze) {
        if (maze == null) {
            throw new IllegalArgumentException();
        }

        this.start = maze.start;
        this.end = maze.end;
        this.grid = maze.grid;
    }

    private boolean notValidArgs(Cell[][] grid, Coordinate start, Coordinate end) {
        if (grid == null || grid.length == 0) {
            return true;
        }

        int height = grid.length;
        int width = grid[0].length;

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
        return grid.length;
    }

    public int width() {
        return grid[0].length;
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
