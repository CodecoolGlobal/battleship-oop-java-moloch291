package com.codecool.battleship.ships;

public enum ShipType {
    CARRIER(5),
    CRUISER(4),
    BATTLESHIP(3),
    SUBMARINE(2),
    DESTROYER(1);

    ShipType(int size) {
    }
}
