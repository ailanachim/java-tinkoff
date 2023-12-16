package edu.project4;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import javax.imageio.ImageIO;

public class Main {

    private Main() {
    }

    public static void main(String[] args) throws IOException {
        // parameters
        final int width = 1080;
        final int height = 720;
        final int points = 1000;
        final int it = 500;
        final int startIt = 20;
        final int transformCount = 5;
        final int threads = 4;
        final double gamma = 2.2;

        LinearTransformer[] linears = new LinearTransformer[transformCount];
        for (int i = 0; i < linears.length; i++) {
            linears[i] = LinearTransformer.getRandom();
        }

//        var finalTransform = new SinTransformer();
//        var finalTransform = new SphericalTransformer();
        var finalTransform = new HeartTransformer();
//        var finalTransform = new DiscTransformer();

        // generation
        List<Pixel> pixels = Collections.synchronizedList(new ArrayList<>(it * points));

        try (ExecutorService executor = Executors.newFixedThreadPool(threads)) {
            for (int j = 0; j < points; j++) {
                executor.execute(() -> {
                    ThreadLocalRandom random = ThreadLocalRandom.current();
                    int k = random.nextInt(linears.length);
                    Pixel pixel = new Pixel(Point.getRandomPoint(random), linears[k].coef);

                    for (int i = -startIt; i < it; i++) {
                        k = random.nextInt(linears.length);
                        pixel = linears[k].transform(pixel);

                        if (i >= 0) {
                            Pixel rendered = finalTransform.transform(pixel);
                            if (Math.abs(rendered.point().x()) < 1 && Math.abs(rendered.point().y()) < 1) {
                                pixels.add(rendered);
                            }
                        }
                    }
                });
            }
        }

        // correction
        int[][] counter = new int[width][height];
        final AtomicInteger max = new AtomicInteger();

        try (ExecutorService executor = Executors.newFixedThreadPool(threads)) {
            for (int i = 0; i < threads; i++) {
                int finalI = i;
                executor.execute(() -> {
                    int offset = finalI * pixels.size() / threads;
                    for (int j = 0; j < pixels.size() / threads; j++) {
                        Pixel pixel = pixels.get(j + offset);
                        int x = pixel.point().getNormalX(width);
                        int y = pixel.point().getNormalY(height);
                        counter[x][y]++;

                        if (counter[x][y] > max.get()) {
                            max.set(counter[x][y]);
                        }
                    }
                });
            }

            for (int i = 0; i < threads; i++) {
                int finalI = i;
                executor.execute(() -> {
                    int offset = finalI * pixels.size() / threads;
                    for (int j = 0; j < pixels.size() / threads; j++) {
                        Pixel pixel = pixels.get(j + offset);
                        int x = pixel.point().getNormalX(width);
                        int y = pixel.point().getNormalY(height);
                        pixel.color().gammaCorrect(counter[x][y], max.get(), gamma);
                    }
                });
            }
        }

        // rendering
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (var pixel : pixels) {
            bi.setRGB(pixel.point().getNormalX(width), pixel.point().getNormalY(height), pixel.color().getRgb());
        }

        ImageIO.write(bi, "PNG", new File("frame.png"));
    }

}
