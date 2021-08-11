package com.codecool.battleship.board;

public enum SquareStatus {
    EMPTY('~'),
    SHIP('#'),
    HIT('H'),
    MISS('M');

    char symbol;

    SquareStatus(char symbol) {
        this.symbol = symbol;
    }
}
