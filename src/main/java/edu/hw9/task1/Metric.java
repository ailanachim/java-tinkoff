package edu.hw9.task1;

public record Metric(double sum, double avg, double min, double max) {

    public static Metric create(double[] data) {
        if (data == null || data.length == 0) {
            throw new IllegalArgumentException();
        }

        double sum = 0;
        double min = data[0];
        double max = data[0];

        for (var elem : data) {
            sum += elem;

            if (elem < min) {
                min = elem;
            } else if (elem > max) {
                max = elem;
            }
        }

        return new Metric(sum, sum / data.length, min, max);
    }
}
