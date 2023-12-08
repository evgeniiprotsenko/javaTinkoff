package edu.hw9.Task2;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.RecursiveTask;

public class DirectoryFinder extends RecursiveTask<Map<String, Integer>> {
    private final File directory;

    public DirectoryFinder(File directory) {
        this.directory = directory;
    }

    @Override
    protected Map<String, Integer> compute() {
        Map<String, Integer> result = new HashMap<>();
        Map<String, DirectoryFinder> subtasks = new HashMap<>();

        File[] files = directory.listFiles();

        if (files != null) {
            int totalFiles = files.length;

            for (File file : files) {
                if (file.isDirectory()) {
                    DirectoryFinder subtask = new DirectoryFinder(file);
                    subtasks.put(file.getAbsolutePath(), subtask);
                    subtask.fork();
                }
            }

            for (Map.Entry<String, DirectoryFinder> entry : subtasks.entrySet()) {
                String subdirectoryPath = entry.getKey();
                DirectoryFinder subtask = entry.getValue();

                Map<String, Integer> subdirectoryFileCounts = subtask.join();
                result.putAll(subdirectoryFileCounts);
                totalFiles += subdirectoryFileCounts.get(subdirectoryPath);
            }

            result.put(directory.getAbsolutePath(), totalFiles);
        }

        return result;
    }
}
