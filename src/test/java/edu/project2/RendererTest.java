package edu.project2;

import org.junit.jupiter.api.Test;
import java.util.List;
import static edu.project2.Cell.PASSAGE;
import static edu.project2.Cell.WALL;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RendererTest {

    @Test
    void mazeRenderTest() {
        Cell[][] grid = new Cell[][] {
            {WALL, WALL, WALL},
            {PASSAGE, PASSAGE, PASSAGE},
            {PASSAGE, WALL, PASSAGE}
        };
        Maze maze = new Maze(grid, new Coordinate(2, 0), new Coordinate(2, 2));

        Renderer renderer = new DefaultRenderer();

        assertThat(renderer.render(maze)).isEqualTo(
            """
                ███%s\
                \s\s\s%s\
                ∘█●%s\
                """.formatted(System.lineSeparator(), System.lineSeparator(), System.lineSeparator())
        );
    }

    @Test
    void solvedMazeRenderTest() {
        Cell[][] grid = new Cell[][] {
            {WALL, WALL, WALL},
            {PASSAGE, PASSAGE, PASSAGE},
            {PASSAGE, WALL, PASSAGE}
        };
        Maze maze = new Maze(grid, new Coordinate(2, 0), new Coordinate(2, 2));
        SolvedMaze solvedMaze = new SolvedMaze(maze, new Path(List.of(
            new Coordinate(2, 0),
            new Coordinate(1, 0),
            new Coordinate(1, 1),
            new Coordinate(1, 2),
            new Coordinate(2, 2)
        )));

        Renderer renderer = new DefaultRenderer();

        assertThat(renderer.render(solvedMaze)).isEqualTo(
            """
                ███%s\
                xxx%s\
                ∘█●%s\
                """.formatted(System.lineSeparator(), System.lineSeparator(), System.lineSeparator())
        );
    }
}
