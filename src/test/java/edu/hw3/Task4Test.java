package edu.hw3;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Task4Test {

    Task4 task4 = new Task4();
    @ParameterizedTest
    @MethodSource("testData")
    public void testConvertToRoman(int num, String expected) {
        String result = task4.convertToRoman(num);
        assertEquals(expected, result);
    }

    static List<Arguments> testData() {
        return List.of(
            Arguments.of(2, "II"),
            Arguments.of(12, "XII"),
            Arguments.of(16, "XVI"),
            Arguments.of(4000, "MMMM")
        );
    }
}
