package com.codecool.battleship.game;

import com.codecool.battleship.board.Square;
import com.codecool.battleship.board.SquareStatus;
import com.codecool.battleship.ships.ShipType;
import com.codecool.battleship.util.Display;
import com.codecool.battleship.util.Input;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final Display display = new Display();
    private final Input input = new Input();

    public void playRound() {}

    public boolean hasWon() {
        return false;
    }

    public List<Square> placeShip(ShipType type) {
        List<Square> positionList = new ArrayList<>();
        int[] shipNosePosition = getStartingCoordinate(type);
        Orientation shipOriented = getShipOrientation(type);
        positionList.add(new Square(shipNosePosition[0], shipNosePosition[1], SquareStatus.SHIP));
        fillUpPositionList(type, positionList, shipNosePosition, shipOriented);
        return positionList;
    }

    private void fillUpPositionList(
            ShipType type,
            List<Square> positionList,
            int[] shipNosePosition,
            Orientation shipOriented
    ) {
        int multiplierForShip = 1;
        for (int addition = 0; addition < type.getSize() - 1; addition++) {
            positionList.add(new Square(shipNosePosition[0] + shipOriented.getX() * multiplierForShip,
                                        shipNosePosition[1] + shipOriented.getY() * multiplierForShip,
                                        SquareStatus.SHIP));
            multiplierForShip++;
        }
    }

    private Orientation getShipOrientation(ShipType type) {
        display.askForOrientation();
        Orientation shipOriented = defineOrientation(input.askForOrientation(), type);
        return shipOriented;
    }

    private int[] getStartingCoordinate(ShipType type) {
        display.askForCoordinates(type);
        int[] shipNosePosition = input.toCoordinates(input.inputCoordinate());
        return shipNosePosition;
    }

    private Orientation defineOrientation(String input, ShipType type) {
        Orientation output = null;
        switch (input) {
            case "n" -> output = Orientation.NORTH;
            case "w" -> output = Orientation.WEST;
            case "s" -> output = Orientation.SOUTH;
            case "e" -> output = Orientation.EAST;
            default -> placeShip(type);
        } return output;
    }
}
