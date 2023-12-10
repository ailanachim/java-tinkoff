package edu.project4;

public final class Color {
    private double red;
    private double green;
    private double blue;

    public Color(double red, double green, double blue) {
        if (red > 1 || red < 0) {
            throw new IllegalArgumentException("red brightness must be between 0 and 1");
        }
        if (green > 1 || green < 0) {
            throw new IllegalArgumentException("green brightness must be between 0 and 1");
        }
        if (blue > 1 || blue < 0) {
            throw new IllegalArgumentException("blue brightness must be between 0 and 1");
        }
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public static Color getRandomColor() {
        return new Color(Math.random(), Math.random(), Math.random());
    }

    public int getRgb() {
        return new java.awt.Color((float) red, (float) green, (float) blue).getRGB();
    }

    public void gammaCorrect(int count, int max, double gamma) {
        double normal = Math.log10(count) / Math.log10(max);
        double coef = Math.pow(normal, 1 / gamma);

        red *= coef;
        green *= coef;
        blue *= coef;
    }

    public double red() {
        return red;
    }

    public double green() {
        return green;
    }

    public double blue() {
        return blue;
    }
}
