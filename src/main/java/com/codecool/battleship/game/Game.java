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

        display.askForCoordinates(type);
        int[] shipNosePosition = input.toCoordinates(input.inputCoordinate());

        display.askForOrientation();
        Orientation shipOriented = defineOrientation(input.askForOrientation());

        positionList.add(new Square(shipNosePosition[0], shipNosePosition[1], SquareStatus.SHIP));
        for (int addition = 0; addition < type.getSize() - 1; addition++) {
            positionList.add(new Square(shipNosePosition[0], shipNosePosition[1], SquareStatus.SHIP));
        }
        return positionList;
    }

    private Orientation defineOrientation(String input) {
        Orientation output;
        switch (input) {
            case "n" -> output = Orientation.NORTH;
            case "w" -> output = Orientation.WEST;
            case "s" -> output = Orientation.SOUTH;
            case "e" -> output = Orientation.EAST;
            default -> throw new IllegalStateException("Unexpected value: " + input);
        } return output;
    }
}
