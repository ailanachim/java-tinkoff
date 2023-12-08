package edu.project4;

public record Point(double x, double y) {

//    public Point {
//        if (x > 1 || x < -1) {
//            throw new IllegalArgumentException("X must be between -1 and 1");
//        }
//
//        if (y > 1 || y < -1) {
//            throw new IllegalArgumentException("Y must be between -1 and 1");
//        }
//    }

    public static Point getRandomPoint() {
        return new Point((Math.random() * 2) - 1, (Math.random() * 2) - 1);
    }

    public int getNormalX(int width) {
        return (int) (width * (x + 1) / 2);
    }

    public int getNormalY(int height) {
        return (int) (height * (y + 1) / 2);
    }
}
