package edu.hw3;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class Task3Test {

    Task3 task3 = new Task3();

    @ParameterizedTest
    @MethodSource("testData")
    public void freqDict(List<Object> input, Map<Object, Integer> expected) {
        Map<Object, Integer> result = task3.freqDict(input);
        assertEquals(expected, result);
    }

    static List<Arguments> testData() {
        return List.of(
            Arguments.of(List.of("a", "bb", "a", "bb"), Map.of("bb", 2, "a", 2)),
            Arguments.of(List.of("this", "and", "that", "and"), Map.of("that", 1, "and", 2, "this", 1)),
            Arguments.of(List.of("код", "код", "код", "bug"), Map.of("код", 3, "bug", 1)),
            Arguments.of(List.of(1, 1, 2, 2), Map.of(1, 2, 2, 2))
        );
    }
}
