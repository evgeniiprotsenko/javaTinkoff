package edu.hw3;

public class Task1 {
    private static final char A = 'a';
    private static final char Z = 'z';

    public String atbash(String str) {
        StringBuilder sb = new StringBuilder();
        for (char letter : str.toCharArray()) {
            boolean upperCase = false;
            char modifiedLetter = letter;
            if (Character.isUpperCase(letter)) {
                upperCase = true;
                modifiedLetter = Character.toLowerCase(letter);
            }
            if (modifiedLetter >= A && modifiedLetter <= Z) {
                int charIndexDifference = (int) Z - (int) modifiedLetter;
                int charIndex = (int) A + charIndexDifference;
                modifiedLetter = (char) charIndex;
                if (!upperCase) {
                    sb.append(modifiedLetter);
                } else {
                    sb.append(Character.toUpperCase(modifiedLetter));
                }
            } else {
                sb.append(modifiedLetter);
            }
        }
        return sb.toString();
    }
}
