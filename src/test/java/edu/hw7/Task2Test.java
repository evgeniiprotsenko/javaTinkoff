package edu.hw7;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import static org.junit.jupiter.api.Assertions.*;

class Task2Test {
    Task2 task2 = new Task2();

    @Test
    void getNumberFactorial() {

        int number = 5;
        BigInteger expectedFactorial = BigInteger.valueOf(120);

        BigInteger actualFactorial = task2.getNumberFactorial(number);

        assertEquals(expectedFactorial, actualFactorial);
    }
}
