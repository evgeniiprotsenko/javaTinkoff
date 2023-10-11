package edu.hw1;

public class Task8 {
    private static final int[] MOVE_COLUMN = {-1, 1, 2, 2, 1, -1, -2, -2};
    private static final int[] MOVE_ROW = {-2, -2, -1, 1, 2, 2, 1, -1};

    public boolean knightBoardCapture(Integer[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] == 1) {
                    for (int k = 0; k < MOVE_COLUMN.length; k++) {
                        int newRow = i + MOVE_ROW[k];
                        int newColumn = j + MOVE_COLUMN[k];
                        if (newRow >= 0 & newColumn >= 0 & newRow < array.length - 1
                                & newColumn < array[i].length - 1) {
                            if (array[newRow][newColumn] == 1) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

}
