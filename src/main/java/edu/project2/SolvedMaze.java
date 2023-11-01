package edu.project2;

public final class SolvedMaze extends Maze {

    private final Path path;

    public SolvedMaze(Maze maze, Path path) {
        super(maze);

        if (path == null) {
            throw new IllegalArgumentException();
        }

        if (path.iterator().next() != maze.start()
            || path.iterator(path.length()).previous() != maze.end()) {
            throw new IllegalArgumentException();
        }

        for (Coordinate coord : path) {
            if (maze.get(coord) != Cell.PASSAGE) {
                throw new IllegalArgumentException();
            }
        }

        this.path = path;
    }

    public Path getPath() {
        return path;
    }
}
