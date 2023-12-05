package edu.hw6;

import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

public interface AbstractFilter extends DirectoryStream.Filter<Path> {

    boolean accept(Path entry);

    default AbstractFilter and(AbstractFilter other) {
        return entry -> accept(entry) && other.accept(entry);
    }


    AbstractFilter REGULAR_FILE = Files::isRegularFile;

    AbstractFilter READABLE = Files::isReadable;

    static AbstractFilter largerThan(long size) {
        return entry -> {
            try {
                return Files.size(entry) > size;
            } catch (Exception ignore) {
                return false;
            }
        };
    }

    static AbstractFilter magicNumber(int... magicBytes) {
        return entry -> {
            try {
                byte[] bytes = Files.readAllBytes(entry);
                if (bytes.length >= magicBytes.length) {
                    for (int i = 0; i < magicBytes.length; i++) {
                        if (bytes[i] != (byte) magicBytes[i]) {
                            return false;
                        }
                    }
                    return true;
                }
            } catch (Exception ignore) {
            }
            return false;
        };
    }

    static AbstractFilter globMatches(String glob) {
        return entry -> entry.getFileName().toString().matches(glob);
    }

    static AbstractFilter regexContains(String regex) {
        return entry -> entry.getFileName().toString().matches(regex);
    }
}
