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

    public void placement(Board board, Player activePlayer, List<Ship> fleet, Game game, int placementOption) {
        display.clearConsole();
        for (ShipType type : Arrays.asList(
                ShipType.CARRIER,
                ShipType.CRUISER,
                ShipType.BATTLESHIP,
                ShipType.SUBMARINE,
                ShipType.DESTROYER
        )) {
            display.printPlacementPhaseHeader(activePlayer);
            display.printBoard(board, activePlayer);
            boolean isPlaced = false;
            if (placementOption == 1) {
                while (!isPlaced) {
                    if (placeShip (board, fleet, new Ship(game.placeShip(type, board, placementOption), type)))
                        isPlaced = true;
                }
            } else {
                while (!isPlaced) {
                    if (placeShip (board, fleet, new Ship(game.placeShip(type, board, placementOption), type)))
                        isPlaced = true;
                }
            }
            display.clearConsole();
        }
    }

    public void randomPlacement(Board board, Player activePlayer, List<Ship> fleet, Game game) {
        display.clearConsole();
        for (ShipType type : Arrays.asList(
                ShipType.CARRIER,
                ShipType.CRUISER,
                ShipType.BATTLESHIP,
                ShipType.SUBMARINE,
                ShipType.DESTROYER
        )) {
            display.printPlacementPhaseHeader(activePlayer);
            display.printBoard(board, activePlayer);
            boolean isPlaced = false;

            display.printPlacementPhaseHeader(activePlayer);
            display.printBoard(board, activePlayer);
            display.clearConsole();
        }
    }

    private boolean placeShip(Board board, List<Ship> fleet, Ship ship) {
        if (!board.isPlacementOkay(ship, board)) {
            return false;
        }
        fleet.add(ship);
        boardPlacement(ship, board);
        return true;
    }

    public void boardPlacement(Ship ship, Board board) {
        Square[][] table = board.getBoard();
        for (Square coordinate : ship.getPlacement()) {
            table[coordinate.getX()][coordinate.getY()].setSquareStatus(SquareStatus.SHIP);
        }
    }


}
