package com.codecool.battleship.ships;

import com.codecool.battleship.board.Square;

import java.util.List;

public class Ship {
    private final List<Square> placement;
    private final ShipType type;

    public Ship(List<Square> placement, ShipType type) {
        this.placement = placement;
        this.type = type;
    }

    public List<Square> getPlacement() {
        return placement;
    }

    public ShipType getType() {
        return type;
    }
}
