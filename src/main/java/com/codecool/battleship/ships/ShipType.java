package com.codecool.battleship.ships;

public enum ShipType {
    CARRIER("carrier", 5),
    CRUISER("cruiser", 4),
    BATTLESHIP("battleship" ,3),
    SUBMARINE("submarine" ,2),
    DESTROYER("destroyer",1);

    private String name;
    private int size;

    ShipType(String name, int size) {
        this.name = name;
        this.size = size;
    }
}
