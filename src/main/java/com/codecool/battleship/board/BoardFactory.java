package com.codecool.battleship.board;

import com.codecool.battleship.game.Game;
import com.codecool.battleship.game.Player;
import com.codecool.battleship.ships.Ship;
import com.codecool.battleship.ships.ShipType;
import com.codecool.battleship.util.Display;

import java.util.ArrayList;
import java.util.List;

public class BoardFactory {
    public void manualPlacement(Board board, Player player, List<Ship> fleet, Game game) {
        fleet.add(new Ship(game.placeShip(ShipType.CARRIER,board) , ShipType.CARRIER));
        boardPlacement(fleet,board);
        fleet.add(new Ship(game.placeShip(ShipType.CRUISER,board) , ShipType.CRUISER));
        boardPlacement(fleet,board);
        fleet.add(new Ship(game.placeShip(ShipType.BATTLESHIP, board) , ShipType.BATTLESHIP));
        boardPlacement(fleet,board);
        fleet.add(new Ship(game.placeShip(ShipType.SUBMARINE, board) , ShipType.SUBMARINE));
        boardPlacement(fleet,board);
        fleet.add(new Ship(game.placeShip(ShipType.DESTROYER, board) , ShipType.DESTROYER));
        boardPlacement(fleet,board);
    }

    public void boardPlacement(List<Ship> fleet, Board board){
        Square[][] table = board.getBoard();
        for (Ship ship : fleet){
            for(Square coordinate : ship.getPlacement()){
                table[coordinate.getX()][coordinate.getY()].setSquareStatus(SquareStatus.SHIP);
            }
        }
    }

    public void randomPlacement() {

    }
}
