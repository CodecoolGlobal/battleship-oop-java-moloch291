package com.codecool.battleship.board;

import com.codecool.battleship.game.Game;
import com.codecool.battleship.game.Player;
import com.codecool.battleship.ships.Ship;
import com.codecool.battleship.ships.ShipType;
import com.codecool.battleship.util.Display;

import java.util.Arrays;
import java.util.List;

public class BoardFactory {
    Display display = new Display();

    public void manualPlacement(Board board, Player activePlayer, List<Ship> fleet, Game game) {
        display.clearConsole();
        for (ShipType type : Arrays.asList(ShipType.CARRIER,
                                           ShipType.CRUISER,
                                           ShipType.BATTLESHIP,
                                           ShipType.SUBMARINE,
                                           ShipType.DESTROYER)) {
            display.printBoard(board, activePlayer);
            placeShip(board, fleet, new Ship(game.placeShip(type,board) , type));
            display.clearConsole();
        }
    }

    private void placeShip(Board board, List<Ship> fleet, Ship ship) {
        fleet.add(ship);
        boardPlacement(ship, board);
    }

    public void boardPlacement(Ship ship, Board board){
        Square[][] table = board.getBoard();
        for(Square coordinate : ship.getPlacement()){
                table[coordinate.getX()][coordinate.getY()].setSquareStatus(SquareStatus.SHIP);
            }
        }

    public void randomPlacement() {

    }
}
