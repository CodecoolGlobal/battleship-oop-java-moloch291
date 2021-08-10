package com.codecool.battleship.board;

import java.util.Arrays;

public abstract class Board {
    private final int size;

    public Board(int size) {
        this.size = size;
    }

    public boolean isPlacementOkay() {
        return false;
    }

    public abstract void printOcean(int size);

    public abstract void printDetailedOcean(int size);

    public int getSize() {
        return size;
    }

    public abstract Square[][] getBoard();

}
