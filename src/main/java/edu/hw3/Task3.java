package edu.hw3;

import java.util.HashMap;
import java.util.List;

public class Task3 {

    public <T> HashMap<T, Integer> freqDict(List<T> list) {
        HashMap<T, Integer> map = new HashMap<>();
        for (T item : list) {
            map.put(item, map.getOrDefault(item, 0) + 1);
        }
        return map;
    }

}
