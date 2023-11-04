package edu.project2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BfsSolver implements Solver {

    @Override
    public SolvedMaze solve(Maze maze) {
        if (maze == null) {
            throw new IllegalArgumentException();
        }

        boolean[][] used = new boolean[maze.height()][maze.width()];
        int[] prev = new int[maze.height() * maze.width()];

        visit(used, prev, maze);

        if (prev[index(maze.end(), maze.width())] == 0) {
            return null;
        }

        List<Coordinate> path = restorePath(prev, maze);

        return new SolvedMaze(maze, new Path(path));
    }

    private void visit(boolean[][] used, int[] prev, Maze maze) {
        Queue<Coordinate> queue = new LinkedList<>();

        queue.add(maze.start());

        while (!queue.isEmpty()) {
            Coordinate current = queue.poll();
            if (current.equals(maze.end())) {
                break;
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
                    queue.add(next);
                    prev[index(next, maze.width())] = index(current, maze.width());
                }
            }
        }
    }

    private List<Coordinate> restorePath(int[] prev, Maze maze) {
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

    private int index(Coordinate coordinate, int width) {
        return coordinate.row() * width + coordinate.col();
    }
}
