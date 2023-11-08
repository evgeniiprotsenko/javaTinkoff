package edu.hw3;

public class Task4 {

    private static final String[] HUNDREDS = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
    private static final String[] TENS = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
    private static final String[] ONES = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

    private static final Integer THOUSAND = 1000;
    private static final Integer HUNDRED = 100;
    private static final Integer TEN = 10;

    public String convertToRoman(Integer number) {
        StringBuilder sb = new StringBuilder();
        Integer currentNumber = number;
        while (currentNumber >= THOUSAND) {
            sb.append("M");
            currentNumber -= THOUSAND;
        }

        sb.append(HUNDREDS[currentNumber / HUNDRED]);
        currentNumber = currentNumber % HUNDRED;
        sb.append(TENS[currentNumber / TEN]);
        currentNumber = currentNumber % TEN;
        sb.append(ONES[currentNumber % TEN]);

        return sb.toString();
    }

}
