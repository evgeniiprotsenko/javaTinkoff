package edu.hw3;

import java.util.ArrayList;
import java.util.List;

public class Task2 {

    public List<String> clusterize(String str) {
        int count = 0;
        StringBuilder sb = new StringBuilder();
        List<String> list = new ArrayList<>();
        for (char letter : str.toCharArray()) {
            if (letter == '(') {
                count++;
            } else if (letter == ')') {
                count--;
            }
            sb.append(letter);
            if (count == 0) {
                list.add(String.valueOf(sb));
                sb = new StringBuilder();
            }
        }
        return list;
    }
}
