package com.codecool.battleship.game;

public enum Orientation {
    NORTH(-1, 0, "N"),
    WEST(0, -1, "W"),
    SOUTH(1, 0, "S"),
    EAST(0, 1, "E");

    private final String name;
    private final int X;
    private final int Y;

    public String getName() {
        return name;
    }

    Orientation(int X, int Y, String name) {
        this.X = X;
        this.Y = Y;
        this.name = name;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }
}
