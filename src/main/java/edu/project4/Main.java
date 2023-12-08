package edu.project4;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Main {

    public static void main(String[] args) throws IOException {
        int width = 1080;
        int height = 720;
        int n = 10;
        int it = 20;

        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        var affine1 = new LinearTransformer(0.8, -0.5, 0, 0.8, 0.5, 0.5, new edu.project4.Color(0.8, 0.7, 0.9));
        var affine2 = new LinearTransformer(0.6, 0.2, 0.3, 0.4, 0.5, 0.1, new edu.project4.Color(0.6, 0.3, 0.5));
        var affine3 = new LinearTransformer(-0.6, 0.4, 0.36, -0.2, 0.5, -0.4, new edu.project4.Color(0.9, 0.5, 0.9));
        var affine4 = new LinearTransformer(0.43, 0.42, 0.07, 0.27, 0.46, 0.6, new edu.project4.Color(0.6, 0.8, 0.2));
        var affine5 = new LinearTransformer(0.86, 0.4, 0.05, 0.4, 0.38, 0.27, new edu.project4.Color(0.1, 0.5, 1));
        var affine6 = new LinearTransformer(0.88, 0.6, -0.3, 0.75, 0.6, -0.2, new edu.project4.Color(1, 1, 0.2));

        Transformer[] linears = new Transformer[] {affine1, affine2, affine3, affine4, affine5, affine6};

        var sinus = new SinTransformer();
        var heart = new HeartTransformer();

        for (int j = 0; j < 1000000; j++) {
            Pixel pixel = new Pixel(Point.getRandomPoint(), edu.project4.Color.getRandomColor());

            for (int i = -it; i < n; i++) {
                int k = (int) (Math.random() * linears.length);
                pixel = linears[k].transform(pixel);

                if (i >= 0) {

                    Pixel rendered = heart.transform(pixel);

                    if (Math.abs(rendered.point().x()) < 1 && Math.abs(rendered.point().y()) < 1) {
                        bi.setRGB(
                            rendered.point().getNormalX(width),
                            rendered.point().getNormalY(height),
                            rendered.color().getRgb()
                        );
                    }
                }
            }
        }

        ImageIO.write(bi, "PNG", new File("my_picture.png"));
    }

}
