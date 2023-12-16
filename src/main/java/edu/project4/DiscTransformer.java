package edu.project4;

public class DiscTransformer implements Transformer {
    @Override
    public Pixel transform(Pixel pixel) {
        double x = pixel.point().x();
        double y = pixel.point().y();

        double r = Math.sqrt(x * x + y * y);
        double t = Math.atan(y / x) / Math.PI;
        final double compression = 1.5;
        double newX = compression * t * Math.sin(Math.PI * r);
        double newY = compression * t * Math.cos(Math.PI * r);

        return new Pixel(new Point(newX, newY), pixel.color());
    }
}
