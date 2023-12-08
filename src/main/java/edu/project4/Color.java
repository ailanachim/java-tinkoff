package edu.project4;

public record Color(double red, double green, double blue) {

    public Color {
        if (red > 1 || red < 0) {
            throw new IllegalArgumentException("red brightness must be between 0 and 1");
        }
        if (green > 1 || green < 0) {
            throw new IllegalArgumentException("green brightness must be between 0 and 1");
        }
        if (blue > 1 || blue < 0) {
            throw new IllegalArgumentException("blue brightness must be between 0 and 1");
        }
    }

    public static Color getRandomColor() {
        return new Color(Math.random(), Math.random(), Math.random());
    }

    public int getRgb() {
        return new java.awt.Color((float) red, (float) green, (float) blue).getRGB();
    }
}
