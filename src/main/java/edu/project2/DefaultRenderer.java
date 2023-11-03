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

        stringBuilder.setCharAt(maze.start().row() * (maze.width() + 1) + maze.start().col(), '∘');
        stringBuilder.setCharAt(maze.end().row() * (maze.width() + 1) + maze.end().col(), '●');

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
            char ch;
            if (coord.equals(maze.start())) {
                ch = '∘';
            } else if (coord.equals(maze.end())) {
                ch = '●';
            } else {
                ch = 'x';
            }
            stringBuilder.setCharAt(coord.row() * (maze.width() + 1) + coord.col(), ch);
        }

        return stringBuilder.toString();
    }
}
