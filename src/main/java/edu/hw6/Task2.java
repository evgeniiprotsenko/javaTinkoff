package edu.hw6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Task2 {
    public void cloneFile(Path path) {
        try {
            Path fileDirectory = path.getParent();
            String fileNameWithoutEnding = getFileNameWithoutEnding(path.getFileName().toString());
            String fileEnding = getFileEnding(path.getFileName().toString());

            int copyNumber = 1;
            Path destinationPath = path;

            while (Files.exists(destinationPath)) {
                String copySuffix = (copyNumber == 1) ? " — копия" : " — копия (" + copyNumber + ")";
                String copyFileName = fileNameWithoutEnding + copySuffix + fileEnding;
                destinationPath = fileDirectory.resolve(copyFileName);
                copyNumber++;
            }

            Files.copy(path, destinationPath);

        } catch (IOException ignore) {
        }
    }

    private String getFileNameWithoutEnding(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? fileName : fileName.substring(0, dotIndex);
    }

    private String getFileEnding(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex);
    }
}
