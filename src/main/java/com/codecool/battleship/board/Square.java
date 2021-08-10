package com.codecool.battleship.board;

public class Square {
    private final int X;
    private final int Y;
    private SquareStatus squareStatus;

    public SquareStatus showSquareStatus() {
        return null;
    }

    public Square(int x, int y, SquareStatus squareStatus) {
        X = x;
        Y = y;
        this.squareStatus = squareStatus;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public SquareStatus getSquareStatus() {
        return squareStatus;
    }

    public void setSquareStatus(SquareStatus squareStatus) {
        this.squareStatus = squareStatus;
    }
}
