package edu.project2;

public class DefaultRenderer implements Renderer {
    @Override
    public String render(Maze maze) {
        if (maze == null) {
            throw new IllegalArgumentException();
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < maze.height(); i++) {
            for (int j = 0; j < maze.width(); j++) {
                if (maze.get(i, j) == Cell.WALL) {
                    stringBuilder.append('█');
                } else {
                    stringBuilder.append(' ');
                }
            }
            stringBuilder.append('\n');
        }

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
                    stringBuilder.append('█');
                } else {
                    stringBuilder.append(' ');
                }
            }
            stringBuilder.append('\n');
        }

        for (Coordinate coord : maze.getPath()) {
            stringBuilder.setCharAt(coord.row() * (maze.width() + 1) + coord.col(), 'x');
        }

        return stringBuilder.toString();
    }
}
