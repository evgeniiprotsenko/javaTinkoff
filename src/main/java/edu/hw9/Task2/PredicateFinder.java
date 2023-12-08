package edu.hw9.Task2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class PredicateFinder extends RecursiveTask<List<File>> {

    private final File directory;
    private final String fileExtension;

    public PredicateFinder(File directory, String fileExtension) {
        this.directory = directory;
        this.fileExtension = fileExtension;
    }

    @Override
    protected List<File> compute() {
        List<File> result = new ArrayList<>();
        List<PredicateFinder> subtasks = new ArrayList<>();

        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    PredicateFinder subtask = new PredicateFinder(file, fileExtension);
                    subtasks.add(subtask);
                    subtask.fork();
                } else {
                    if (file.getName().toLowerCase().endsWith(fileExtension.toLowerCase())) {
                        result.add(file);
                    }
                }
            }

            for (PredicateFinder subtask : subtasks) {
                result.addAll(subtask.join());
            }
        }

        return result;
    }
}
