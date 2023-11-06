package edu.project2;

public class DefaultRenderer implements Renderer {

    private static final char WALL = '█';
    private static final char PASSAGE = ' ';
    private static final char START = '∘';
    private static final char END = '●';
    private static final char PATH = 'x';

    @Override
    public String render(Maze maze) {
        if (maze == null) {
            throw new IllegalArgumentException();
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < maze.height(); i++) {
            for (int j = 0; j < maze.width(); j++) {
                if (maze.get(i, j) == Cell.WALL) {
                    stringBuilder.append(WALL);
                } else {
                    stringBuilder.append(PASSAGE);
                }
            }
            stringBuilder.append(System.lineSeparator());
        }

        stringBuilder.setCharAt(
            maze.start().row() * (maze.width() + System.lineSeparator().length()) + maze.start().col(),
            START
        );
        stringBuilder.setCharAt(
            maze.end().row() * (maze.width() + System.lineSeparator().length()) + maze.end().col(),
            END
        );

        return stringBuilder.toString();
    }

    @Override
    public String render(SolvedMaze maze) {
        if (maze == null) {
            throw new IllegalArgumentException();
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < maze.height(); i++) {
            for (int j = 0; j < maze.width(); j++) {
                if (maze.get(i, j) == Cell.WALL) {
                    stringBuilder.append(WALL);
                } else {
                    stringBuilder.append(PASSAGE);
                }
            }
            stringBuilder.append(System.lineSeparator());
        }

        for (Coordinate coord : maze.getPath()) {
            char ch;
            if (coord.equals(maze.start())) {
                ch = START;
            } else if (coord.equals(maze.end())) {
                ch = END;
            } else {
                ch = PATH;
            }
            stringBuilder.setCharAt(coord.row() * (maze.width() + System.lineSeparator().length()) + coord.col(), ch);
        }

        return stringBuilder.toString();
    }
}
