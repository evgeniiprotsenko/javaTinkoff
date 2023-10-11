package edu.hw1;

import java.util.ArrayList;

public class Task5 {

    public boolean isPalindromeDescendant(Integer number) {

        if (testForPalindrome(number)) {
            return true;
        }

        if (number.toString().length() % 2 == 1) {
            return false;
        }

        ArrayList<Integer> childrenList = getChildren(number);

        for (Integer child : childrenList) {
            if (testForPalindrome(child)) {
                return true;
            }
        }

        return false;
    }

    private static boolean testForPalindrome(Integer number) {
        String numberStr = number.toString();
        String reversedStr = new StringBuilder(numberStr).reverse().toString();
        return numberStr.equals(reversedStr);
    }

    private static ArrayList<Integer> getChildren(Integer parent) {

        ArrayList<Integer> children = new ArrayList<>();
        String child = parent.toString();
        int sum;
        StringBuilder sb = new StringBuilder();

        while (child.length() > 1 & child.length() % 2 == 0) {
            for (int i = 0; i < child.length() - 1; i += 2) {
                sum = Character.getNumericValue(child.charAt(i)) + Character.getNumericValue(child.charAt(i + 1));
                sb.append(sum);
            }
            if (sb.length() > 1) {
                child = sb.toString();
                children.add(Integer.parseInt(child));
                sb = new StringBuilder();
            } else {
                break;
            }
        }
        return children;
    }
}
