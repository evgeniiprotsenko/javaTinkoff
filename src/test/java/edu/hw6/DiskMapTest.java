package edu.hw6;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;

class DiskMapTest {

    @Test
    public void testDiskMapOperations() {

        Path path = Paths.get("src/test/java/edu/hw6/Task1/res.txt");
        Path path2 = Paths.get("src/test/java/edu/hw6/Task1/res2.txt");

        try {
            Files.deleteIfExists(path);
            Files.deleteIfExists(path2);
        } catch (IOException e) {
            e.printStackTrace();
        }

        DiskMap diskMap = new DiskMap(path.toString());

        // Проверка добавления элементов
        diskMap.put("key1", "val1");
        diskMap.put("key2", "val2");
        assertEquals("[key1=val1, key2=val2]", diskMap.entrySet().toString());

        // Проверка удаления элемента
        diskMap.remove("key1");
        assertEquals("[key2=val2]", diskMap.entrySet().toString());

        // Проверка сохранения в новый файл
        diskMap.saveToFile(path2.toString());
        diskMap.put("key3", "val3");
        assertEquals("[key2=val2, key3=val3]", diskMap.entrySet().toString());

        // Проверка загрузки из другого файла
        diskMap.loadFromFile(path.toString());
        assertEquals("[key2=val2]", diskMap.entrySet().toString());
    }

    @Test
    public void testPutAndGet() {
        Path path = Paths.get("src/test/java/edu/hw6/Task1/res.txt");

        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        DiskMap diskMap = new DiskMap(path.toString());

        diskMap.put("key1", "val1");
        diskMap.put("key2", "val2");

        assertEquals("val1", diskMap.get("key1"));
        assertEquals("val2", diskMap.get("key2"));
    }

    @Test
    public void testSizeAndContains() {
        Path path = Paths.get("src/test/java/edu/hw6/Task1/res.txt");
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        DiskMap diskMap = new DiskMap(path.toString());

        diskMap.put("key1", "val1");
        diskMap.put("key2", "val2");

        assertEquals(2, diskMap.size());
        assertTrue(diskMap.containsKey("key1"));
        assertFalse(diskMap.containsKey("key3"));
    }

    @Test
    public void testClearAndEmpty() {
        Path path = Paths.get("src/test/java/edu/hw6/Task1/res.txt");
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        DiskMap diskMap = new DiskMap(path.toString());

        diskMap.put("key1", "val1");
        diskMap.put("key2", "val2");

        diskMap.clear();

        assertTrue(diskMap.isEmpty());
    }

}
