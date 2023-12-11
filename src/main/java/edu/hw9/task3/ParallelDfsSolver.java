package edu.hw9.task3;

import edu.project2.Coordinate;
import edu.project2.DfsSolver;
import edu.project2.Maze;
import edu.project2.Path;
import edu.project2.SolvedMaze;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class ParallelDfsSolver extends DfsSolver {

    private ForkJoinPool pool;

    @Override
    public SolvedMaze solve(Maze maze) {
        if (maze == null) {
            throw new IllegalArgumentException();
        }

        boolean[][] used = new boolean[maze.height()][maze.width()];
        int[] prev = new int[maze.height() * maze.width()];

        pool = new ForkJoinPool();

        visit(maze.start(), used, prev, maze);

        pool.close();

        if (prev[index(maze.end(), maze.width())] == 0) {
            return null;
        }

        List<Coordinate> path = restorePath(prev, maze);

        return new SolvedMaze(maze, new Path(path));
    }

    @Override
    protected void visit(Coordinate current, boolean[][] used, int[] prev, Maze maze) {
        pool.execute(() -> {
                if (!used[current.row()][current.col()]) {
                    super.visit(current, used, prev, maze);
                }
            }
        );
    }
}
