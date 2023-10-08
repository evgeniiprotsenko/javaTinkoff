package edu.hw1;

public class Task1 {
    private static final String REGEX = "^(\\d{2,}):([0-5]\\d)$";

    public static int minutesToSeconds(String oldLength) {
        if (!oldLength.matches(REGEX)) {
            return -1;
        }
        String[] numbers = oldLength.split(":");
        int min = Integer.parseInt(numbers[0]);
        int sec = Integer.parseInt(numbers[1]);
        return 60 * min + sec;
    }
}
