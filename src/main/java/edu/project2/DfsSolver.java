package edu.project2;

import java.util.LinkedList;
import java.util.List;

public class DfsSolver implements Solver {

    Maze maze = null;

    @Override
    public SolvedMaze solve(Maze maze) {
        if (maze == null) {
            throw new IllegalArgumentException();
        }

        this.maze = maze;

        boolean[][] used = new boolean[maze.height()][maze.width()];
        int[] prev = new int[maze.height() * maze.width()];

        visit(maze.start(), used, prev);

        if (prev[index(maze.end())] == 0) {
            return null;
        }

        List<Coordinate> path = restorePath(prev);

        return new SolvedMaze(maze, new Path(path));
    }

    private void visit(Coordinate coord, boolean[][] used, int[] prev) {
        int x = coord.row();
        int y = coord.col();

        used[x][y] = true;

        if (maze.get(x + 1, y) == Cell.PASSAGE && !used[x + 1][y]) {
            prev[index(new Coordinate(x + 1, y))] = index(coord);
            visit(new Coordinate(x + 1, y), used, prev);
        }

        if (maze.get(x - 1, y) == Cell.PASSAGE && !used[x - 1][y]) {
            prev[index(new Coordinate(x - 1, y))] = index(coord);
            visit(new Coordinate(x - 1, y), used, prev);
        }

        if (maze.get(x, y + 1) == Cell.PASSAGE && !used[x][y + 1]) {
            prev[index(new Coordinate(x, y + 1))] = index(coord);
            visit(new Coordinate(x, y + 1), used, prev);
        }

        if (maze.get(x, y - 1) == Cell.PASSAGE && !used[x][y - 1]) {
            prev[index(new Coordinate(x, y - 1))] = index(coord);
            visit(new Coordinate(x, y - 1), used, prev);
        }
    }

    private List<Coordinate> restorePath(int[] prev) {
        List<Coordinate> path = new LinkedList<>();
        Coordinate coord = maze.end();

        while (!coord.equals(maze.start())) {
            path.add(0, coord);

            int index = prev[index(coord)];
            int x = index / maze.width();
            int y = index % maze.width();
            coord = new Coordinate(x, y);
        }

        path.add(0, maze.start());

        return path;
    }

    private int index(Coordinate coordinate) {
        return coordinate.row() * maze.width() + coordinate.col();
    }
}
