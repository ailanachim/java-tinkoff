package edu.hw7.task4;

public class PiApproximator {

    protected int totalCount;

    public PiApproximator(int pointCount) {
        totalCount = pointCount;
    }

    public double calculatePi() throws InterruptedException {
        int circleCount = 0;

        for (int i = 0; i < totalCount; i++) {
            double x = (Math.random() * 2) - 1;
            double y = (Math.random() * 2) - 1;

            if (x * x + y * y <= 1) {
                circleCount++;
            }
        }

        final int coefficient = 4;
        return coefficient * (double) circleCount / totalCount;
    }
}
