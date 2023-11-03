package edu.project2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static edu.project2.Cell.*;

public class MazeTest {

    @Test
    @DisplayName("Grid is empty")
    void invalidArgsTest1() {
        Cell[][] grid = new Cell[0][];
        assertThatThrownBy(() -> new Maze(grid, new Coordinate(0, 0), new Coordinate(1, 1))).isInstanceOf(
            IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Grid rows are not the same width")
    void invalidArgsTest2() {
        Cell[][] grid = new Cell[][] {
            {WALL, WALL},
            {PASSAGE, PASSAGE, PASSAGE}
        };

        assertThatThrownBy(() -> new Maze(grid, new Coordinate(1, 0), new Coordinate(1, 2))).isInstanceOf(
            IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Start coordinate is out of range")
    void invalidArgsTest3() {
        Cell[][] grid = new Cell[][] {
            {WALL, WALL, WALL},
            {PASSAGE, PASSAGE, PASSAGE},
            {PASSAGE, WALL, PASSAGE}
        };
        assertThatThrownBy(() -> new Maze(grid, new Coordinate(2, 3), new Coordinate(1, 1))).isInstanceOf(
            IllegalArgumentException.class);
    }

    @Test
    @DisplayName("End coordinate is out of range")
    void invalidArgsTest4() {
        Cell[][] grid = new Cell[][] {
            {WALL, WALL, WALL},
            {PASSAGE, PASSAGE, PASSAGE},
            {PASSAGE, WALL, PASSAGE}
        };
        assertThatThrownBy(() -> new Maze(grid, new Coordinate(1, 0), new Coordinate(3, 1))).isInstanceOf(
            IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Start cell is wall")
    void invalidArgsTest5() {
        Cell[][] grid = new Cell[][] {
            {WALL, WALL, WALL},
            {PASSAGE, PASSAGE, PASSAGE},
            {PASSAGE, WALL, PASSAGE}
        };
        assertThatThrownBy(() -> new Maze(grid, new Coordinate(0, 0), new Coordinate(1, 1))).isInstanceOf(
            IllegalArgumentException.class);
    }

    @Test
    @DisplayName("End cell is wall")
    void invalidArgsTest6() {
        Cell[][] grid = new Cell[][] {
            {WALL, WALL, WALL},
            {PASSAGE, PASSAGE, PASSAGE},
            {PASSAGE, WALL, PASSAGE}
        };
        assertThatThrownBy(() -> new Maze(grid, new Coordinate(1, 0), new Coordinate(2, 1))).isInstanceOf(
            IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Path is empty")
    void invalidArgsTest7() {
        Cell[][] grid = new Cell[][] {
            {WALL, WALL, WALL},
            {PASSAGE, PASSAGE, PASSAGE},
            {PASSAGE, WALL, PASSAGE}
        };
        Maze maze = new Maze(grid, new Coordinate(2, 0), new Coordinate(2, 2));

        assertThatThrownBy(() -> new SolvedMaze(maze, new Path(List.of()))).isInstanceOf(
            IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Path is interrupted")
    void invalidArgsTest8() {
        Cell[][] grid = new Cell[][] {
            {WALL, WALL, WALL},
            {PASSAGE, PASSAGE, PASSAGE},
            {PASSAGE, WALL, PASSAGE}
        };
        Maze maze = new Maze(grid, new Coordinate(2, 0), new Coordinate(2, 2));

        assertThatThrownBy(() -> new SolvedMaze(
            maze,
            new Path(List.of(new Coordinate(2, 0), new Coordinate(2, 2)))
        )).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Path goes throw a wall")
    void invalidArgsTest9() {
        Cell[][] grid = new Cell[][] {
            {WALL, WALL, WALL},
            {PASSAGE, PASSAGE, PASSAGE},
            {PASSAGE, WALL, PASSAGE}
        };
        Maze maze = new Maze(grid, new Coordinate(2, 0), new Coordinate(2, 2));

        assertThatThrownBy(() -> new SolvedMaze(
            maze,
            new Path(List.of(new Coordinate(2, 0), new Coordinate(2, 1), new Coordinate(2, 2)))
        )).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Path doesn't match with start and end coordinate")
    void invalidArgsTest10() {
        Cell[][] grid = new Cell[][] {
            {WALL, WALL, WALL},
            {PASSAGE, PASSAGE, PASSAGE},
            {PASSAGE, WALL, PASSAGE}
        };
        Maze maze = new Maze(grid, new Coordinate(2, 0), new Coordinate(2, 2));

        assertThatThrownBy(() -> new SolvedMaze(
            maze,
            new Path(List.of(new Coordinate(1, 0), new Coordinate(1, 1), new Coordinate(1, 2)))
        )).isInstanceOf(IllegalArgumentException.class);
    }
}
