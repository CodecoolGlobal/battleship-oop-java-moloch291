package com.codecool.battleship.ships;

public enum ShipType {
    CARRIER("carrier", 5),
    CRUISER("cruiser", 4),
    BATTLESHIP("battleship" ,3),
    SUBMARINE("submarine" ,2),
    DESTROYER("destroyer",1);

    private final String name;
    private final int size;

    ShipType(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }
}
