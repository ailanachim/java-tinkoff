package edu.project2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class DfsSolver implements Solver {

    @Override
    public SolvedMaze solve(Maze maze) {
        if (maze == null) {
            throw new IllegalArgumentException();
        }

        boolean[][] used = new boolean[maze.height()][maze.width()];
        int[] prev = new int[maze.height() * maze.width()];

        visit(maze.start(), used, prev, maze);

        if (prev[index(maze.end(), maze.width())] == 0) {
            return null;
        }

        List<Coordinate> path = restorePath(prev, maze);

        return new SolvedMaze(maze, new Path(path));
    }

    protected void visit(Coordinate current, boolean[][] used, int[] prev, Maze maze) {
        if (current.equals(maze.end())) {
            return;
        }

        int x = current.row();
        int y = current.col();

        used[x][y] = true;

        List<Coordinate> near = Arrays.asList(
            new Coordinate(x - 1, y),
            new Coordinate(x + 1, y),
            new Coordinate(x, y - 1),
            new Coordinate(x, y + 1)
        );

        for (Coordinate next : near) {
            if (maze.get(next) == Cell.PASSAGE && !used[next.row()][next.col()]) {
                prev[index(next, maze.width())] = index(current, maze.width());
                visit(next, used, prev, maze);
            }
        }
    }

    protected List<Coordinate> restorePath(int[] prev, Maze maze) {
        List<Coordinate> path = new LinkedList<>();
        Coordinate coord = maze.end();

        while (!coord.equals(maze.start())) {
            path.add(0, coord);

            int index = prev[index(coord, maze.width())];
            int x = index / maze.width();
            int y = index % maze.width();
            coord = new Coordinate(x, y);
        }

        path.add(0, maze.start());

        return path;
    }

    protected int index(Coordinate coordinate, int width) {
        return coordinate.row() * width + coordinate.col();
    }
}
