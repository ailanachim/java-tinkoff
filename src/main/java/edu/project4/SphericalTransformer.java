package edu.project4;

public class SphericalTransformer implements Transformer {
    @Override
    public Pixel transform(Pixel pixel) {
        double x = pixel.point().x();
        double y = pixel.point().y();

        double r = x * x + y * y;
        double newX = x / r;
        double newY = y / r;

        return new Pixel(new Point(newX, newY), pixel.color());
    }
}
