package com.codecool.battleship.game;

import com.codecool.battleship.board.Board;
import com.codecool.battleship.board.BoardFactory;
import com.codecool.battleship.ships.Ship;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Ship> ships;
    private final String name;

    public Player(String name, Game game, Board board) {
        this.name = name;
        this.ships = loadUpFleet(game, board);
    }


    private List<Ship> loadUpFleet(Game game, Board board) {
        List<Ship> fleet = new ArrayList<>();
        BoardFactory manualPlace = new BoardFactory();
        manualPlace.manualPlacement(board, this, fleet, game);
        return fleet;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public String getName() {
        return name;
    }

    public boolean isAlive() {
        return false;
    }

    public Player(String name) {
        this.name = name;
    }
}
