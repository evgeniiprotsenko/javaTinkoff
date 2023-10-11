package edu.hw1;

import java.util.Arrays;

public class Task6 {

    public static Integer countK(Integer n) {

        String currentNumber = n.toString();
        int count = 0;
        while (!currentNumber.equals("6174")) {
            currentNumber = operation(currentNumber);

            count++;
        }
        return count;
    }


    private static String operation(String number) {

        char[] digits = number.toCharArray();
        StringBuilder min = new StringBuilder();

        Arrays.sort(digits);
        for (char digit : digits) {
            min.append(digit);
        }
        StringBuilder max = new StringBuilder(min).reverse();

        int difference = Integer.parseInt(max.toString()) - Integer.parseInt(min.toString());
        return Integer.toString(difference);
    }

}
