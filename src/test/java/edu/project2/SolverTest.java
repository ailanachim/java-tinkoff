package edu.project2;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static edu.project2.Cell.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SolverTest {

    static Arguments[] solvers() {
        return new Arguments[] {Arguments.of(new DfsSolver()), Arguments.of(new BfsSolver())};
    }

    @ParameterizedTest()
    @MethodSource("solvers")
    void solvedTest(Solver solver) {
        Cell[][] grid = new Cell[][] {
            {WALL, WALL, WALL, WALL, WALL},
            {WALL, PASSAGE, PASSAGE, PASSAGE, WALL},
            {WALL, WALL, PASSAGE, WALL, WALL},
            {WALL, WALL, PASSAGE, PASSAGE, WALL},
            {WALL, WALL, WALL, WALL, WALL}
        };

        Maze maze = new Maze(grid, new Coordinate(1, 1), new Coordinate(3, 3));

        SolvedMaze solvedMaze = solver.solve(maze);
        Path path = solvedMaze.getPath();

        assertThat(path.iterator(0).next()).isEqualTo(new Coordinate(1, 1));
        assertThat(path.iterator(1).next()).isEqualTo(new Coordinate(1, 2));
        assertThat(path.iterator(2).next()).isEqualTo(new Coordinate(2, 2));
        assertThat(path.iterator(3).next()).isEqualTo(new Coordinate(3, 2));
        assertThat(path.iterator(4).next()).isEqualTo(new Coordinate(3, 3));
    }

    @ParameterizedTest()
    @MethodSource("solvers")
    void noSolutionTest(Solver solver) {
        Cell[][] grid = new Cell[][] {
            {WALL, WALL, WALL, WALL, WALL},
            {WALL, PASSAGE, PASSAGE, PASSAGE, WALL},
            {WALL, WALL, WALL, WALL, WALL},
            {WALL, WALL, PASSAGE, PASSAGE, WALL},
            {WALL, WALL, WALL, WALL, WALL}
        };

        Maze maze = new Maze(grid, new Coordinate(1, 1), new Coordinate(3, 3));

        assertThat(solver.solve(maze)).isNull();
    }
}
