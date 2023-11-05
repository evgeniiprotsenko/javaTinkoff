package edu.project2;

public class Cell {
    public enum Type {WALL, PASSAGE}

    private final int row;
    private final int col;
    private Type type;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public Cell(int row, int col, Type type) {
        this.row = row;
        this.col = col;
        this.type = type;
    }


    public int row() {
        return row;
    }

    public int col() {
        return col;
    }

    public Type type() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
