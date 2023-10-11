package edu.hw1;

public class Task7 {

    public static int rotateRight(int n, int shift) {
        String binaryString = String.format("%4s", Integer.toBinaryString(n)).replace(' ', '0');
        int length = binaryString.length();
        for (int i = 0; i < shift; i++) {
            binaryString = "".concat(String.valueOf(binaryString.charAt(length - 1))).concat(binaryString.substring(0, length - 1));
        }
        return Integer.parseInt(binaryString, 2);
    }

    public static int rotateLeft(int n, int shift) {
        String binaryString = String.format("%4s", Integer.toBinaryString(n)).replace(' ', '0');
        for (int i = 0; i < shift; i++) {
            binaryString = binaryString.substring(1).concat(String.valueOf(binaryString.charAt(0)));
        }
        return Integer.parseInt(binaryString, 2);
    }

}
