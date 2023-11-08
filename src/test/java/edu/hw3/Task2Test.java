package edu.hw3;

import org.junit.jupiter.params.ParameterizedTest;

import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Task2Test {

    Task2 task2 = new Task2();

    @ParameterizedTest
    @MethodSource("testData")
    public void testClusterize(String input, List<String> expected) {
        List<String> result = task2.clusterize(input);
        assertEquals(expected, result);
    }

    static List<Arguments> testData() {
        return List.of(
            Arguments.of("()()()", List.of("()", "()", "()")),
            Arguments.of("((()))", List.of("((()))")),
            Arguments.of("((()))(())()()(()())", List.of("((()))", "(())", "()", "()", "(()())")),
            Arguments.of("((())())(()(()()))", List.of("((())())", "(()(()()))"))
        );
    }
}
