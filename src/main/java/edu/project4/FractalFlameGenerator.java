package edu.project4;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.imageio.ImageIO;

public class FractalFlameGenerator {

    private static float rand(float min, float max) {
        Random random = new Random();
        return min + random.nextFloat() * (max - min);
    }

    private static float sphericalX(float x, float y) {
        return x / (x * x + y * y);
    }

    private static float sphericalY(float x, float y) {
        return y / (x * x + y * y);
    }

    private static final float XMIN = -1.777f;
    private static final float XMAX = 1.777f;
    private static final float YMIN = -1.0f;
    private static final float YMAX = 1.0f;

    private static final int THREAD_COUNT = 4;

    private static final int STEP_BEGIN = -20;

    public void generateFractalFlame(int n, int eqCount, int it, int xRes, int yRes) throws IOException {
        Coefficients[] coeffs = new Coefficients[eqCount];
        for (int i = 0; i < eqCount; i++) {
            coeffs[i] = new Coefficients(
                rand(-1.0f, 1.0f), rand(-1.0f, 1.0f), rand(-1.0f, 1.0f),
                rand(-1.0f, 1.0f), rand(-1.0f, 1.0f), rand(-1.0f, 1.0f),
                new Color(rand(0.0f, 1.0f), rand(0.0f, 1.0f), rand(0.0f, 1.0f))
            );
        }

        Pixel[][] pixels = initializePixels(xRes, yRes);

        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);

        for (int num = 0; num < n; num++) {
            final int currentNum = num;
            Pixel[][] finalPixels = pixels;
            executor.execute(() -> {
                float newX = rand(XMIN, XMAX);
                float newY = rand(YMIN, YMAX);

                for (int step = STEP_BEGIN; step < it; step++) {
                    int i = (int) rand(0, eqCount);
                    float x = coeffs[i].a * newX + coeffs[i].b * newY + coeffs[i].c;
                    float y = coeffs[i].d * newX + coeffs[i].e * newY + coeffs[i].f;

                    if (step >= 0 && newX >= XMIN && newX <= XMAX && newY >= YMIN && newY <= YMAX) {
                        int x1 = xRes - (int) (((XMAX - newX) / (XMAX - XMIN)) * xRes);
                        int y1 = yRes - (int) (((YMAX - newY) / (YMAX - YMIN)) * yRes);

                        if (x1 < xRes && y1 < yRes) {
                            synchronized (finalPixels[x1][y1]) {
                                if (finalPixels[x1][y1].counter == 0) {
                                    finalPixels[x1][y1].red = coeffs[i].color.red;
                                    finalPixels[x1][y1].green = coeffs[i].color.green;
                                    finalPixels[x1][y1].blue = coeffs[i].color.blue;
                                } else {
                                    finalPixels[x1][y1].red = (finalPixels[x1][y1].red + coeffs[i].color.red) / 2;
                                    finalPixels[x1][y1].green = (finalPixels[x1][y1].green + coeffs[i].color.green) / 2;
                                    finalPixels[x1][y1].blue = (finalPixels[x1][y1].blue + coeffs[i].color.blue) / 2;
                                }
                                finalPixels[x1][y1].counter++;
                            }
                        }
                    }
                    newX = sphericalX(x, y);
                    newY = sphericalY(x, y);
                }
            });
        }

        executor.shutdown();
        executor.close();

        while (!executor.isTerminated()) {
        }

        // Гамма-коррекция
        pixels = applyGammaCorrection(pixels, xRes, yRes);

        BufferedImage image = new BufferedImage(xRes, yRes, BufferedImage.TYPE_INT_RGB);

        setRGBValues(image, pixels, xRes, yRes);

        ImageIO.write(image, "png", new File("fractal_flame.png"));
    }

    private static final double MAX = 0.0;
    private static final double GAMMA = 2.2;


    private static Pixel[][] applyGammaCorrection(Pixel[][] pixels, int xRes, int yRes) {
        double max = MAX;
        double gamma = GAMMA;

        // Вычисление максимального значения для нормализации
        for (int row = 0; row < xRes; row++) {
            for (int col = 0; col < yRes; col++) {
                if (pixels[row][col].counter != 0) {
                    pixels[row][col].normal = Math.log10(pixels[row][col].counter);
                    if (pixels[row][col].normal > max) {
                        max = pixels[row][col].normal;
                    }
                }
            }
        }

        // Применение гамма-коррекции
        for (int row = 0; row < xRes; row++) {
            for (int col = 0; col < yRes; col++) {
                pixels[row][col].normal /= max;
                pixels[row][col].red = (float) (pixels[row][col].red * Math.pow(pixels[row][col]
                    .normal, 1.0 / gamma));
                pixels[row][col].green = (float) (pixels[row][col].green * Math.pow(pixels[row][col]
                    .normal, 1.0 / gamma));
                pixels[row][col].blue = (float) (pixels[row][col].blue * Math.pow(pixels[row][col]
                    .normal, 1.0 / gamma));
            }
        }

        return pixels;
    }


    private static Pixel[][] initializePixels(int xRes, int yRes) {
        Pixel[][] pixels = new Pixel[xRes][yRes];
        for (int i = 0; i < xRes; i++) {
            for (int j = 0; j < yRes; j++) {
                pixels[i][j] = new Pixel();
            }
        }
        return pixels;
    }


    private final static int RGB = 255;
    private final static int RED_SHIFT = 16;
    private final static int GREEN_SHIFT = 8;

    private static void setRGBValues(BufferedImage image, Pixel[][] pixels, int xRes, int yRes) {
        for (int i = 0; i < xRes; i++) {
            for (int j = 0; j < yRes; j++) {
                int rgb = ((int) (pixels[i][j].red * RGB) << RED_SHIFT)
                    | ((int) (pixels[i][j].green * RGB) << GREEN_SHIFT)
                    | (int) (pixels[i][j].blue * RGB);
                image.setRGB(i, j, rgb);
            }
        }
    }
}
