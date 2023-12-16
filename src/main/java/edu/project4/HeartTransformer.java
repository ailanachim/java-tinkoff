package edu.project4;

public class HeartTransformer implements Transformer {
    @Override
    public Pixel transform(Pixel pixel) {
        double x = pixel.point().x();
        double y = pixel.point().y();

        double r = Math.sqrt(x * x + y * y);
        double t = 2 * r * Math.atan(y / x);
        final double compression = 0.5;
        final double yOffset = -0.25;
        double newX = compression * r * Math.sin(t);
        double newY = -compression * r * Math.cos(t) + yOffset;

        return new Pixel(new Point(newX, newY), pixel.color());
    }
}
