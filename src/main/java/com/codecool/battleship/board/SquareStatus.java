package com.codecool.battleship.board;

public enum SquareStatus {
    EMPTY('~'),
    SHIP('#'),
    HIT('H'),
    MISS('M');


    SquareStatus(char symbol) {
    }
}
