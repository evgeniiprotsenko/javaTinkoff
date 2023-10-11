package edu.hw1;

public class Task4 {

    public String fixString(String str) {
        if (str.length() > 1) {
            int length = str.length();
            char[] chars = str.toCharArray();

            for (int i = 0; i < length - 1; i += 2) {
                char temp = chars[i];
                chars[i] = chars[i + 1];
                chars[i + 1] = temp;
            }

            return new String(chars);
        }
        return str;
    }

}
