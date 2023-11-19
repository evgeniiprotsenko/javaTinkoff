package edu.hw6;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;

class AbstractFilterTest {

    @Test
    void testFilter() throws IOException {
        Path tempDir = Files.createTempDirectory("testDir");
        Path tempFile1 = Files.createTempFile(tempDir, "testFile1", ".txt");
        Path tempFile2 = Files.createTempFile(tempDir, "testFile2", ".png");
        Path tempFile3 = Files.createTempFile(tempDir, "testFile3", ".jpg");

        try {
            AbstractFilter filter = AbstractFilter.REGULAR_FILE
                .and(AbstractFilter.READABLE)
                .and(AbstractFilter.largerThan(0))
                .and(AbstractFilter.globMatches("*.png"));

            try (DirectoryStream<Path> entries = Files.newDirectoryStream(tempDir, filter)) {
                for (Path entry : entries) {
                    assertEquals(entry, tempFile2);
                    assertNotEquals(entry, tempFile1);
                    assertNotEquals(entry, tempFile3);
                }
            }

            AbstractFilter magicNumbersFilter = AbstractFilter.magicNumber(0x89, 'P', 'N', 'G');

            try (DirectoryStream<Path> entries = Files.newDirectoryStream(tempDir, magicNumbersFilter)) {
                for (Path entry : entries) {
                    assertEquals(entry, tempFile2);
                    assertNotEquals(entry, tempFile1);
                    assertNotEquals(entry, tempFile3);
                }
            }
        } finally {
            Files.deleteIfExists(tempFile1);
            Files.deleteIfExists(tempFile2);
            Files.deleteIfExists(tempFile3);
            Files.deleteIfExists(tempDir);
        }
    }
}
