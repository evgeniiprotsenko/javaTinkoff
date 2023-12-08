package edu.hw9.Task2;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Task2Test {

    Task2 task2 = new Task2();

    @Test
    void findByCount() {
        String path = Paths.get("").toAbsolutePath().toString();
        int expected = 1;
        List<String> result = task2.findByCount(path, 250);
        assertEquals(expected, result.size());
    }

    @Test
    void findByPredicate() {
        String path = Paths.get("").toAbsolutePath().toString();
        int expected = 1;
        List<File> result = task2.findByPredicate(path, ".md");
        assertEquals(expected, result.size());
    }
}
