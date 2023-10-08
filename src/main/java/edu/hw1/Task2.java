package edu.hw1;

public class Task2 {

    public static int countDigits(int number) {
        int count = 0;
        number = Math.abs(number);
        while (number > 0) {
            number /= 10;
            count++;
        }
        return count == 0 ? 1 : count;
    }
}
