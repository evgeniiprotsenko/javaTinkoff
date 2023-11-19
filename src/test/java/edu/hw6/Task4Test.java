package edu.hw6;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;

class Task4Test {

    Task4 task4 = new Task4();

    @Test
    void writeToOutputFile() throws IOException {
        Path tempFilePath = Files.createTempFile("test", ".txt");

        try {
            task4.writeToOutputFile(tempFilePath, "Programming is learned by writing programs. ― " +
                "Brian Kernighan");

            String fileContent = Files.readString(tempFilePath, StandardCharsets.UTF_8);

            assertEquals("Programming is learned by writing programs. ― " +
                "Brian Kernighan\r\n", fileContent);

            assertTrue(Files.exists(tempFilePath));
            assertTrue(Files.size(tempFilePath) > 0);

        } finally {
            Files.deleteIfExists(tempFilePath);
        }
    }
}
