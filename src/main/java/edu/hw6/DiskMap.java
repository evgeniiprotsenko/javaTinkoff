package edu.hw6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class DiskMap implements Map<String, String> {
    private String filePath;
    private final Map<String, String> map;

    public DiskMap(String filePath) {
        this.filePath = filePath;
        this.map = new HashMap<>();
        loadFromFile();
    }

    public void loadFromFile() {
        loadFromFile(filePath);
    }

    public void loadFromFile(String path) {
        try {
            if (!Files.exists(Path.of(path))) {
                return;
            }
            try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
                map.clear();
                reader.lines().forEach(line -> {
                    String[] keyValue = line.split(":");
                    map.put(keyValue[0], keyValue[1]);
                });
            }
            filePath = path;
        } catch (IOException ignore) {
        }
    }

    public void saveToFile() {
        saveToFile(filePath);
    }

    public void saveToFile(String path) {
        try {
            Path savePath = Path.of(path);
            if (!Files.exists(savePath)) {
                Files.createFile(savePath);
            }
            filePath = path;
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    writer.write(entry.getKey() + ":" + entry.getValue());
                    writer.newLine();
                }
            }
        } catch (IOException ignore) {
        }
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public String get(Object key) {
        return map.get(key);
    }

    @Override
    public String put(String key, String value) {
        String oldValue = map.put(key, value);
        saveToFile();
        return oldValue;
    }

    @Override
    public String remove(Object key) {
        String removedValue = map.remove(key);
        saveToFile();
        return removedValue;
    }

    @Override
    public void putAll(Map<? extends String, ? extends String> m) {
        map.putAll(m);
        saveToFile();
    }

    @Override
    public void clear() {
        map.clear();
        saveToFile();
    }

    @Override
    public java.util.Set<String> keySet() {
        return map.keySet();
    }

    @Override
    public java.util.Collection<String> values() {
        return map.values();
    }

    @Override
    public java.util.Set<Map.Entry<String, String>> entrySet() {
        return map.entrySet();
    }
}
