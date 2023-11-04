package edu.project2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private final static Logger LOGGER = LogManager.getLogger();

    private Main() {
    }

    public static void main(String[] args) {
        Generator generator = new RecursiveBacktrackingGenerator();

        final int height = 10;
        final int width = 15;
        Maze maze = generator.generate(height, width);

        Renderer renderer = new DefaultRenderer();
        LOGGER.info(System.lineSeparator() + renderer.render(maze));

        Solver solver = new DfsSolver();

        SolvedMaze solvedMaze = solver.solve(maze);

        LOGGER.info(System.lineSeparator() + renderer.render(solvedMaze));
    }
}
