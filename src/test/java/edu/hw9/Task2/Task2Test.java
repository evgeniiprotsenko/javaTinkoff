package edu.hw9.Task2;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class Task2Test {

    Task2 task2 = new Task2();



    @Test
    void findByCount() {
        String path = Paths.get("").toAbsolutePath() + "\\src";
        List<String> expected = Stream.of(path,
            path + "\\main",
            path + "\\main\\java",
            path + "\\main\\java\\edu",
            path + "\\test",
            path + "\\test\\java",
            path + "\\test\\java\\edu").sorted().toList();
        List<String> result = task2.findByCount(path, 15).stream().sorted().toList();
        assertEquals(expected, result);
    }

    @Test
    void findByPredicate() {
        String path = Paths.get("").toAbsolutePath().toString();
        List<File> expected = List.of(new File(path + "\\README.md"));
        List<File> result = task2.findByPredicate(path, ".md");
        assertEquals(expected, result);
    }
}
