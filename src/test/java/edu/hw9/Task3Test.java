package edu.hw9;

import edu.hw9.task3.ParallelDfsSolver;
import edu.project2.Cell;
import edu.project2.Coordinate;
import edu.project2.Maze;
import edu.project2.Path;
import edu.project2.SolvedMaze;
import org.junit.jupiter.api.Test;
import static edu.project2.Cell.PASSAGE;
import static edu.project2.Cell.WALL;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {

    @Test
    void solvableMazeTest() {
        Cell[][] grid = new Cell[][] {
            {WALL, WALL, WALL, WALL, WALL},
            {WALL, PASSAGE, PASSAGE, PASSAGE, WALL},
            {WALL, WALL, PASSAGE, WALL, WALL},
            {WALL, WALL, PASSAGE, PASSAGE, WALL},
            {WALL, WALL, WALL, WALL, WALL}
        };

        Maze maze = new Maze(grid, new Coordinate(1, 1), new Coordinate(3, 3));

        var solver = new ParallelDfsSolver();
        SolvedMaze solvedMaze = solver.solve(maze);
        Path path = solvedMaze.getPath();

        assertThat(path.iterator(0).next()).isEqualTo(new Coordinate(1, 1));
        assertThat(path.iterator(1).next()).isEqualTo(new Coordinate(1, 2));
        assertThat(path.iterator(2).next()).isEqualTo(new Coordinate(2, 2));
        assertThat(path.iterator(3).next()).isEqualTo(new Coordinate(3, 2));
        assertThat(path.iterator(4).next()).isEqualTo(new Coordinate(3, 3));
    }

    @Test
    void unsolvableMazeTest() {
        Cell[][] grid = new Cell[][] {
            {WALL, WALL, WALL, WALL, WALL},
            {WALL, PASSAGE, PASSAGE, PASSAGE, WALL},
            {WALL, WALL, WALL, WALL, WALL},
            {WALL, WALL, PASSAGE, PASSAGE, WALL},
            {WALL, WALL, WALL, WALL, WALL}
        };

        Maze maze = new Maze(grid, new Coordinate(1, 1), new Coordinate(3, 3));

        var solver = new ParallelDfsSolver();

        assertThat(solver.solve(maze)).isNull();
    }
}
