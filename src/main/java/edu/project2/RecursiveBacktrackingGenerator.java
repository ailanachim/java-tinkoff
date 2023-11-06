package edu.project2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RecursiveBacktrackingGenerator implements Generator {
    @Override
    public Maze generate(int height, int width) {
        final int MIN_HEIGHT = 3;
        final int MIN_WIDTH = 3;

        if (height < MIN_HEIGHT || width < MIN_WIDTH) {
            throw new IllegalArgumentException();
        }

        Cell[][] grid = new Cell[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j] = Cell.WALL;
            }
        }

        // generate random coordinate not on the maze border
        int x1 = (int) (Math.random() * (height - MIN_HEIGHT) + 1);
        int y1 = ((int) (Math.random() * (width - MIN_WIDTH) + 1));
        Coordinate start = new Coordinate(x1, y1);

        makePassage(grid, x1, y1);

        int x2 = (int) (Math.random() * (height - MIN_HEIGHT) + 1);
        int y2 = ((int) (Math.random() * (width - MIN_WIDTH) + 1));

        while (grid[x2][y2] != Cell.PASSAGE || x1 == x2 && y1 == y2) {
            x2 = (int) (Math.random() * (height - MIN_HEIGHT) + 1);
            y2 = ((int) (Math.random() * (width - MIN_WIDTH) + 1));
        }

        Coordinate end = new Coordinate(x2, y2);

        return new Maze(grid, start, end);
    }

    private void makePassage(Cell[][] grid, int x, int y) {
        grid[x][y] = Cell.PASSAGE;

        List<Coordinate> next = Arrays.asList(
            new Coordinate(x - 1, y),
            new Coordinate(x + 1, y),
            new Coordinate(x, y - 1),
            new Coordinate(x, y + 1)
        );

        Collections.shuffle(next);

        for (Coordinate coord : next) {
            if (canMakePassage(grid, coord.row(), coord.col(), x, y)) {
                makePassage(grid, coord.row(), coord.col());
            }
        }
    }

    private boolean canMakePassage(Cell[][] grid, int x, int y, int prevX, int prevY) {
        if (x == 0 || x == grid.length - 1 || y == 0 || y == grid[0].length - 1) {
            return false;
        }

        if (grid[x][y] == Cell.PASSAGE) {
            return false;
        }

        List<Coordinate> next = Arrays.asList(
            new Coordinate(x - 1, y),
            new Coordinate(x + 1, y),
            new Coordinate(x, y - 1),
            new Coordinate(x, y + 1)
        );

        for (Coordinate coord : next) {
            if (grid[coord.row()][coord.col()] == Cell.PASSAGE && !(coord.row() == prevX && coord.col() == prevY)) {
                return false;
            }
        }

        return true;
    }
}
