package com.codecool.battleship.board;

import java.util.Arrays;

public class Player2Board extends Board {
    private final Square[][] ocean;

    public Player2Board(int size) {
        super(size);
        this.ocean = new Square[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                this.ocean[row][col] = new Square(row, col, SquareStatus.EMPTY);
            }
        }
    }

    @Override
    public Square[][] getBoard() {
        return ocean;
    }
}
