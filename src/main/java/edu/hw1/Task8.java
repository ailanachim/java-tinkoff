package edu.hw1;

public class Task8 {

    private Task8() {
    }

    @SuppressWarnings("MagicNumber")
    public static boolean knightBoardCapture(int[][] field) {
        int[][] knightTargetSquares = {{-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}};

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j] == 0) {
                    continue;
                }
                for (int[] square : knightTargetSquares) {
                    int x = i + square[0];
                    int y = j + square[1];
                    if (x >= 0 && x < field.length && y >= 0 && y < field[x].length) {
                        if (field[x][y] == 1) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }
}
