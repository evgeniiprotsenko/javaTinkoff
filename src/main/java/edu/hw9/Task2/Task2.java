package edu.hw9.Task2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;

public class Task2 {

    public List<String> findByCount(String path, int count) {

        File rootDirectory = new File(path);

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        DirectoryFinder counter = new DirectoryFinder(rootDirectory);

        Map<String, Integer> map = forkJoinPool.invoke(counter);

        forkJoinPool.close();

        List<String> result = new ArrayList<>();

        map.forEach((s, integer) -> {
            if (integer > count) {
                result.add(s);
            }
        });

        return result;
    }

    public List<File> findByPredicate(String path, String predicate) {

        File rootDirectory = new File(path);

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        PredicateFinder finder = new PredicateFinder(rootDirectory, predicate);

        List<File> result = forkJoinPool.invoke(finder);

        forkJoinPool.close();

        return result;
    }
}
