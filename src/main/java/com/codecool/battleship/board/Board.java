package com.codecool.battleship.board;

public class Board {
    private final int size;
    private Square[][] ocean;

    public Board(int size) {
        this.size = size;
        this.ocean = new Square[size][size];
    }

    public boolean isPlacementOkay() {
        return false;
    }
}
