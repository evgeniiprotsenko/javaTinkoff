package edu.hw6;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;

class Task2Test {

    Task2 task2 = new Task2();

    @Test
    void cloneFile() throws IOException {
        Path testDir = Paths.get("src/test/java/edu/hw6/Task2");

        Path originalFile = testDir.resolve("original.txt");
        Files.createFile(originalFile);

        task2.cloneFile(originalFile);
        task2.cloneFile(originalFile);

        String copiedFileName = "original — копия.txt";
        Path copiedFilePath = testDir.resolve(copiedFileName);
        assertTrue(Files.exists(copiedFilePath));

        String copiedFileName2 = "original — копия (2).txt";
        Path copiedFilePath2 = testDir.resolve(copiedFileName2);
        assertTrue(Files.exists(copiedFilePath2));

        Files.list(testDir)
            .filter(Files::isRegularFile)
            .forEach(file -> {
                try {
                    Files.delete(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
    }
}

