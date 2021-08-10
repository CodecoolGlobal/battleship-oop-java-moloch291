package com.codecool.battleship.game;

import com.codecool.battleship.ships.Ship;
import com.codecool.battleship.ships.ShipType;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Ship> ships;
    private final String name;

    public Player(String name, Game game) {
        this.name = name;
        this.ships = loadUpFleet(game);
    }

    private List<Ship> loadUpFleet(Game game) {
        List<Ship> fleet = new ArrayList<>();
        fleet.add(new Ship(game.placeShip(ShipType.CARRIER) , ShipType.CARRIER));
        fleet.add(new Ship(game.placeShip(ShipType.CRUISER) , ShipType.CRUISER));
        fleet.add(new Ship(game.placeShip(ShipType.BATTLESHIP) , ShipType.BATTLESHIP));
        fleet.add(new Ship(game.placeShip(ShipType.SUBMARINE) , ShipType.SUBMARINE));
        fleet.add(new Ship(game.placeShip(ShipType.DESTROYER) , ShipType.DESTROYER));
        return fleet;
    }

    public boolean isAlive() {
        return true;
    }

    public Player(String name) {
        this.name = name;
    }
}
