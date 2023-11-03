package edu.project2;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BfsSolver implements Solver {

    Maze maze;

    @Override
    public SolvedMaze solve(Maze maze) {
        if (maze == null) {
            throw new IllegalArgumentException();
        }

        this.maze = maze;

        boolean[][] used = new boolean[maze.height()][maze.width()];
        int[] prev = new int[maze.height() * maze.width()];

        visit(used, prev);

        if (prev[index(maze.end())] == 0) {
            return null;
        }

        List<Coordinate> path = restorePath(prev);

        return new SolvedMaze(maze, new Path(path));
    }

    private void visit(boolean[][] used, int[] prev) {
        Queue<Coordinate> queue = new LinkedList<>();

        queue.add(maze.start());

        while (!queue.isEmpty()) {
            Coordinate coord = queue.poll();
            if (coord.equals(maze.end())) {
                break;
            }

            int x = coord.row();
            int y = coord.col();
            used[x][y] = true;

            if (maze.get(x + 1, y) == Cell.PASSAGE && !used[x + 1][y]) {
                Coordinate next = new Coordinate(x + 1, y);
                queue.add(next);
                prev[index(next)] = index(coord);
            }

            if (maze.get(x - 1, y) == Cell.PASSAGE && !used[x - 1][y]) {
                Coordinate next = new Coordinate(x - 1, y);
                queue.add(next);
                prev[index(next)] = index(coord);
            }

            if (maze.get(x, y + 1) == Cell.PASSAGE && !used[x][y + 1]) {
                Coordinate next = new Coordinate(x, y + 1);
                queue.add(next);
                prev[index(next)] = index(coord);
            }

            if (maze.get(x, y - 1) == Cell.PASSAGE && !used[x][y - 1]) {
                Coordinate next = new Coordinate(x, y - 1);
                queue.add(next);
                prev[index(next)] = index(coord);
            }
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
