package edu.project4;

public class HeartTransformer implements Transformer {
    @Override
    public Pixel transform(Pixel pixel) {
        double x = pixel.point().x();
        double y = pixel.point().y();

        double r = Math.sqrt(x * x + y * y);
        double t = r * Math.atan(y / x);
        double newX = 0.5 * r * Math.sin(t);
        double newY = -0.5 * r * Math.cos(t) - 0.25;

        return new Pixel(new Point(newX, newY), pixel.color());
    }
}
