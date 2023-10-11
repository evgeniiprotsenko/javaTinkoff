package edu.hw1;

public class Task8 {
    public static boolean knightBoardCapture(Integer[][] array) {

        int[] moveColumn = {-1, 1, 2, 2, 1, -1, -2, -2};
        int[] moveRow = {-2, -2, -1, 1, 2, 2, 1, -1};

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] == 1) {
                    for (int k = 0; k < 8; k++) {
                        int newRow = i + moveRow[k];
                        int newColumn = j + moveColumn[k];
                        if (newRow >= 0 & newColumn >= 0 & newRow < array.length - 1 & newColumn < array[i].length - 1) {
                            if (array[newRow][newColumn] == 1) return false;
                        }
                    }
                }
            }
        }
        return true;
    }

}
