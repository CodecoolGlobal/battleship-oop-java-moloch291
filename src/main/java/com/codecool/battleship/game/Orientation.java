package com.codecool.battleship.game;

public enum Orientation {
    NORTH(-1, 0),
    WEST(0, 1),
    SOUTH(1, 0),
    EAST(0, -1);

    private int X;
    private int Y;

    Orientation(int X, int Y) {
        this.X = X;
        this.Y = Y;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }
}
