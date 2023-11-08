package edu.hw3;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Task7Test {

    @ParameterizedTest
    @MethodSource("provideArguments")
    public void testCompare(String o1, String o2, int expected) {
        Task7 comparator = new Task7();
        int result = comparator.compare(o1, o2);
        assertEquals(expected, result);
    }

    static List<Arguments> provideArguments() {
        return List.of(
            Arguments.of("key1", "key2", -1),
            Arguments.of("key2", "key1", 1),
            Arguments.of("key", "key", 0),
            Arguments.of(null, "key", -1),
            Arguments.of("key", null, 1),
            Arguments.of(null, null, 0)
        );
    }
}
