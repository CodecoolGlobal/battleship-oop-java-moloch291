package com.codecool.battleship.game;

import com.codecool.battleship.board.*;
import com.codecool.battleship.ships.ShipType;
import com.codecool.battleship.util.Display;
import com.codecool.battleship.util.Input;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final Display display = new Display();
    private final Input input = new Input();

    public void gameLoop(int size) {
        display.askForName();
        Player player1 = new Player(input.askForName(), game);
        display.askForName();
        Player player2 = new Player(input.askForName(), game);
        Player1Board player1Board = new Player1Board(size);
        Player2Board player2Board = new Player2Board(size);
    }

    public void playRound(Player activePlayer, Board board) {
        display.turn(activePlayer);
        String shootArea = input.inputCoordinate();
        int[] shootCoordinates = input.toCoordinates(shootArea);
        int row = shootCoordinates[0];
        int col = shootCoordinates[1];
        Square square = board.getBoard()[row][col];
        SquareStatus status = square.getSquareStatus();
        switch (status) {
            case EMPTY:
                square.setSquareStatus(SquareStatus.MISS);
            case SHIP:
                square.setSquareStatus(SquareStatus.HIT);
            default:
                break;
        }
        if (hasWon(activePlayer)) {
            display.printResults();
        }

    }


    public boolean hasWon(Player activePlayer) {
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
        return defineOrientation(input.askForOrientation(), type);
    }

    private int[] getStartingCoordinate(ShipType type) {
        display.askForCoordinates(type);
        return input.toCoordinates(input.inputCoordinate());
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
