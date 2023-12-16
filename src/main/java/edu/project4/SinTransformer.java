package edu.project4;

public class SinTransformer implements Transformer {
    @Override
    public Pixel transform(Pixel pixel) {
        return new Pixel(new Point(Math.sin(pixel.point().x()), Math.sin(pixel.point().y())), pixel.color());
    }
}
