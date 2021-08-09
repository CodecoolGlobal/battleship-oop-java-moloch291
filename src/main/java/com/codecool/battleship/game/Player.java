package com.codecool.battleship.game;

import com.codecool.battleship.ships.Ship;

import java.util.List;

public class Player {
    private List<Ship> ships;
    private final String name;

    public boolean isAlive() {
        return true;
    }

    public Player(String name) {
        this.name = name;
    }
}
