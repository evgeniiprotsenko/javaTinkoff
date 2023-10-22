package edu.hw2.task2;

public class Square extends Rectangle {
    private int side;

    public Square() {
        this.side = 0;
    }

    public void setSide(int side) {
        this.side = side;
    }

    @Override
    public double area() {
        return Math.pow(side, 2);
    }
}
