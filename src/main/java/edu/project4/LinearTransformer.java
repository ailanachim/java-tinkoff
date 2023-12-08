package edu.project4;

public class LinearTransformer implements Transformer {

    double a;
    double b;
    double c;
    double d;
    double e;
    double f;
    Color coef;

    public LinearTransformer(double a, double b, double c, double d, double e, double f, Color coef) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.coef = coef;
    }

    @Override
    public Pixel transform(Pixel pixel) {
        double x = a * pixel.point().x() + b * pixel.point().y() + c;
        double y = d * pixel.point().x() + e * pixel.point().y() + f;

        var color = new Color(
            (pixel.color().red() + coef.red()) / 2,
            (pixel.color().green() + coef.green()) / 2,
            (pixel.color().blue() + coef.blue()) / 2
        );

        return new Pixel(new Point(x, y), color);
    }
}
