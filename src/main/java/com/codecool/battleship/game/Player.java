package com.codecool.battleship.game;

import com.codecool.battleship.board.Board;
import com.codecool.battleship.board.BoardFactory;
import com.codecool.battleship.board.Square;
import com.codecool.battleship.ships.Ship;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Ship> ships;
    private List<Ship> sunkShips;
    private final String name;
    private int shipSquares;

    public Player(String name, Game game, Board board, int placementOption) {
        this.name = name;
        this.ships = loadUpFleet(game, board, placementOption);
        this.sunkShips = new ArrayList<>();
        this.shipSquares = 0;
        for (Ship ship : ships) {
            for (Square ignored : ship.getPlacement()) {
                shipSquares++;
            }
        }
    }


    private List<Ship> loadUpFleet(Game game, Board board, int placementOption) {
        List<Ship> fleet = new ArrayList<>();
        BoardFactory manualPlace = new BoardFactory();
        BoardFactory randomPlace = new BoardFactory();
        switch (placementOption) {
            case 2:
                randomPlace.randomPlacement(board, this, fleet, game);
                break;
            default:
                manualPlace.manualPlacement(board, this, fleet, game);
                break;
        }

        return fleet;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public List<Ship> getSunkShips() {
        return sunkShips;
    }

    public String getName() {
        return name;
    }

    public boolean isAlive() {
        return shipSquares > 0;
    }

    public Player(String name) {
        this.name = name;
    }

    public int getShipSquares() {
        return shipSquares;
    }

    public void setShipSquares() {
        this.shipSquares--;
    }
}
