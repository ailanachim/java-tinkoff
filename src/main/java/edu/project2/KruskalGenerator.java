package edu.project2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KruskalGenerator implements Generator {

    private int[] prev = null;
    Cell[][] grid = null;
    private int width;

    @Override
    public Maze generate(int height, int width) {
        final int MIN_HEIGHT = 3;
        final int MIN_WIDTH = 3;

        if (height < MIN_HEIGHT || width < MIN_WIDTH) {
            throw new IllegalArgumentException();
        }

        this.width = width;

        grid = new Cell[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j] = Cell.WALL;
            }
        }

        List<Coordinate> coordinates = new ArrayList<>();
        for (int i = 1; i < height - 1; i++) {
            for (int j = 1; j < width - 1; j++) {
                coordinates.add(new Coordinate(i, j));
            }
        }

        Collections.shuffle(coordinates);

        prev = new int[height * width];
        for (int i = 0; i < prev.length; i++) {
            prev[i] = i;
        }

        for (Coordinate coord : coordinates) {
            if (canMakePassage(coord.row(), coord.col())) {
                makePassage(coord.row(), coord.col());
            }
        }

        Coordinate start = coordinates.get(0);

        int x2 = (int) (Math.random() * (height - MIN_HEIGHT) + 1);
        int y2 = ((int) (Math.random() * (width - MIN_WIDTH) + 1));

        while (grid[x2][y2] != Cell.PASSAGE || x2 == start.row() && y2 == start.col()) {
            x2 = (int) (Math.random() * (height - MIN_HEIGHT) + 1);
            y2 = ((int) (Math.random() * (width - MIN_WIDTH) + 1));
        }

        Coordinate end = new Coordinate(x2, y2);

        return new Maze(grid, start, end);
    }

    private boolean canMakePassage(int x, int y) {
        if (root(index(x + 1, y)) == root(index(x - 1, y))) {
            return false;
        }

        if (root(index(x + 1, y)) == root(index(x, y - 1))) {
            return false;
        }

        if (root(index(x + 1, y)) == root(index(x, y + 1))) {
            return false;
        }

        if (root(index(x - 1, y)) == root(index(x, y - 1))) {
            return false;
        }

        if (root(index(x - 1, y)) == root(index(x, y + 1))) {
            return false;
        }

        return root(index(x, y - 1)) != root(index(x, y + 1));
    }

    private void makePassage(int x, int y) {
        grid[x][y] = Cell.PASSAGE;
        if (grid[x + 1][y] == Cell.PASSAGE) {
            unite(index(x, y), index(x + 1, y));
        }

        if (grid[x - 1][y] == Cell.PASSAGE) {
            unite(index(x, y), index(x - 1, y));
        }

        if (grid[x][y + 1] == Cell.PASSAGE) {
            unite(index(x, y), index(x, y + 1));
        }

        if (grid[x][y - 1] == Cell.PASSAGE) {
            unite(index(x, y), index(x, y - 1));
        }
    }

    private int index(int x, int y) {
        return x * width + y;
    }

    private int root(int c) {
        if (prev[c] == c) {
            return c;
        }

        return prev[c] = root(prev[c]);
    }

    private void unite(int c1, int c2) {
        prev[root(c1)] = root(c2);
    }
}

/*

#########
#########
#########
#########
#########

#########
# #######
#########
#########
#########

#########
# #######
##### ###
#########
#########

#########
# #######
####  ###
#########
#########

#########
# #######
###   ###
#########
#########

#########
# #######
# #   ###
#########
#########

#########
# # #####
# #   ###
#########
#########

#########
#   #####
# #   ###
# # #####
#########

 */
