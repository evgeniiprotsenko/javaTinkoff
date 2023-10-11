package edu.hw1;

public class Task1 {
    private static final String REGEX = "^(\\d{2,}):([0-5]\\d)$";
    private static final int SECONDS = 60;

    public int minutesToSeconds(String oldLength) {
        if (!oldLength.matches(REGEX)) {
            return -1;
        }
        String[] numbers = oldLength.split(":");
        int min = Integer.parseInt(numbers[0]);
        int sec = Integer.parseInt(numbers[1]);
        return SECONDS * min + sec;
    }
}
