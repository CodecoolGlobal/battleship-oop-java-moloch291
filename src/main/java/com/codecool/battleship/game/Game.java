package com.codecool.battleship.game;

import com.codecool.battleship.board.Board;
import com.codecool.battleship.board.Square;
import com.codecool.battleship.board.SquareStatus;
import com.codecool.battleship.util.Display;
import com.codecool.battleship.util.Input;

public class Game {

    public void playRound(int activePlayer, Board board) {
        Display display = new Display();
        display.turn(activePlayer);
        Input input = new Input();
        String shootArea = input.inputCoordinate();
        int[] shootCoordinates = input.toCoordinates(shootArea);
        int row = shootCoordinates[0];
        int col = shootCoordinates[1];
        Square square = board.getSquare()[row][col];
        String status = square.getStatus();
        switch (status) {
            case "EMPTY":
                square.setSquareStatus(SquareStatus.MISS);
            case "SHIP":
                square.setSquareStatus(SquareStatus.HIT);
            default:
                break;
        }
        if (hasWon(activePlayer)) {
            display.printResults();
        }

    }


    public boolean hasWon() {
        return false;
    }
}
