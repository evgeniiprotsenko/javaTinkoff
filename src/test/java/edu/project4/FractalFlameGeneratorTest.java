package edu.project4;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

class FractalFlameGeneratorTest {

    FractalFlameGenerator fractalFlameGenerator = new FractalFlameGenerator();
    @Test
    void generateFractalFlame() {
        try {
            fractalFlameGenerator.generateFractalFlame(1_000_000, 10, 100, 8000, 6000);
            File file = new File("fractal_flame.png");
            assertTrue(file.exists());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
