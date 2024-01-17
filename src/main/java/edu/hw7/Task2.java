package edu.hw7;

import java.math.BigInteger;
import java.util.stream.LongStream;

public class Task2 {

    public BigInteger getNumberFactorial(int number) {

        if (number <= 0) {
            throw new IllegalArgumentException();
        }

        return LongStream.rangeClosed(1, number)
            .parallel()
            .mapToObj(BigInteger::valueOf)
            .reduce(BigInteger.ONE, BigInteger::multiply);
    }

}
