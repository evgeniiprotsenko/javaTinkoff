package edu.hw1;

public class Task2 {

    private final static int ORDEROFNUMBERS = 10;

    public int countDigits(int number) {
        int count = 0;
        int absNumber = Math.abs(number);
        while (absNumber > 0) {
            absNumber /= ORDEROFNUMBERS;
            count++;
        }
        return count == 0 ? 1 : count;
    }
}
