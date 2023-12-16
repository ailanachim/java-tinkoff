package edu.project4;

import java.util.concurrent.ThreadLocalRandom;

public record Point(double x, double y) {

    public static Point getRandomPoint(ThreadLocalRandom random) {
        return new Point(random.nextDouble(-1, 1), random.nextDouble(-1, 1));
    }

    public int getNormalX(int width) {
        return (int) (width * (x + 1) / 2);
    }

    public int getNormalY(int height) {
        return (int) (height * (y + 1) / 2);
    }
}
