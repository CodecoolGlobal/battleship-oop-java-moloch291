package com.codecool.battleship.game;

import com.codecool.battleship.Battleship;
import com.codecool.battleship.board.*;
import com.codecool.battleship.ships.Ship;
import com.codecool.battleship.ships.ShipType;
import com.codecool.battleship.util.Display;
import com.codecool.battleship.util.Input;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class Game {
    private final Display display = new Display();
    private final Input input = new Input();

    public void gameLoop(int size) {

        Player1Board player1Board = new Player1Board(size);
        Player2Board player2Board = new Player2Board(size);
        Player1Radar player1Radar = new Player1Radar(size);
        Player2Radar player2Radar = new Player2Radar(size);
        display.clearConsole();
        display.askForName();
        Player player1 = new Player(input.askForName(), this, player1Board);
        display.clearConsole();
        display.askForName();
        Player player2 = new Player(input.askForName(), this, player2Board);

        int currentRound = 1;
        boolean isRunning = true;
        while (isRunning) {
            Player activePlayer = currentRound % 2 == 0 ? player2 : player1;
            Player opponent = activePlayer == player1 ? player2 : player1;
            Board activeBoard = activePlayer == player1 ? player2Board : player1Board;
            Board activeRadar = activePlayer == player1 ? player2Radar : player1Radar;
            printShipDetails(activePlayer);
            playRound(activePlayer, opponent, activeBoard, activeRadar);
            if (hasWon(opponent) && !opponent.isAlive()) {
                display.clearConsole();
                display.printResults(activePlayer);
                isRunning = false;
            }
            currentRound++;
        }
        display.askForEnter();
        input.askEnter();
        Battleship.main(new String[]{});
    }

    private void printShipDetails(Player activePlayer) {
        for (Ship ship : activePlayer.getShips()) {
            for (Square coordinate : ship.getPlacement()) {
                System.out.println(coordinate.getSquareStatus());
            }
        }
    }

    public void playRound(Player activePlayer, Player opponent, Board board, Board radar) {
        display.turn(activePlayer);
        display.printRadar(radar, activePlayer);
        String shootArea = input.inputCoordinate();
        int[] shootCoordinates = input.toCoordinates(shootArea);
        int row = shootCoordinates[0];
        int col = shootCoordinates[1];
        Square square = board.getBoard()[row][col];
        Square radarSquare = radar.getBoard()[row][col];
        SquareStatus status = square.getSquareStatus();
        switch (status) {
            case EMPTY -> radarSquare.setSquareStatus(SquareStatus.MISS);
            case SHIP -> {
                radarSquare.setSquareStatus(SquareStatus.HIT);
                opponent.setShipSquares();
            }
        }
        display.printRadar(radar, activePlayer);
    }


    public boolean hasWon(Player opponent) {
        return opponent.getShipSquares() == 0;
    }

    public List<Square> placeShip(ShipType type, Board board) {
        List<Square> positionList = new ArrayList<>();
        int[] shipNosePosition = getStartingCoordinate(type, "");
        shipNosePosition = validateNosePosition(type, board, shipNosePosition);
        if (type != ShipType.DESTROYER) {
            return getOrientation(type, board, positionList, shipNosePosition);
        }
        Orientation shipOriented = Orientation.EAST;

        positionList.add(new Square(shipNosePosition[0], shipNosePosition[1], SquareStatus.SHIP));
        fillUpPositionList(type, positionList, shipNosePosition, shipOriented);
        return positionList;
    }

    private List<Square> getOrientation(ShipType type,
                                        Board board,
                                        List<Square> positionList,
                                        int[] shipNosePosition) {
        ArrayList<String> validOrientations = validOrientations(shipNosePosition, type, board);
        Orientation shipOriented = getShipOrientation(type, board, validOrientations);
        while (!validOrientations.contains(shipOriented.getName())) {
            display.wrongCoordinates();
            shipOriented = getShipOrientation(type, board, validOrientations);
        }
        positionList.add(new Square(shipNosePosition[0], shipNosePosition[1], SquareStatus.SHIP));
        fillUpPositionList(type, positionList, shipNosePosition, shipOriented);
        return positionList;
    }

    private int[] validateNosePosition(ShipType type, Board board, int[] shipNosePosition) {
        while (!input.inputValidation(board, input.toString(shipNosePosition))) {
            shipNosePosition = getStartingCoordinate(type, "Enter valid direction!");
        }
        return shipNosePosition;
    }

    private ArrayList<String> validOrientations(int[] shipNosePosition, ShipType type, Board board) {
        ArrayList<String> validDirection = new ArrayList<>();

        if (shipNosePosition[0] - type.getSize() >= 0) {
            validDirection.add("N");
        }
        if (shipNosePosition[1] - type.getSize() >= 0) {
            validDirection.add("W");
        }
        if (shipNosePosition[0] + type.getSize() <= board.getSize()) {
            validDirection.add("S");
        }
        if (shipNosePosition[1] + type.getSize() <= board.getSize()) {
            validDirection.add("E");
        }
        return validDirection;
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

    private Orientation getShipOrientation(ShipType type, Board board, ArrayList<String> validOrientations) {
        display.askForOrientation(validOrientations);
        return defineOrientation(input.inputCoordinate(), type, board);
    }

    private int[] getStartingCoordinate(ShipType type, String message) {
        //display.clearConsole();
        display.askForCoordinates(type);
        display.printMessage('\n' + message + '\n');
        try{
            String currentInputCoordinate = input.inputCoordinate();
            if (Character.isLetter(currentInputCoordinate.charAt(0))) {
                return input.toCoordinates(currentInputCoordinate);
            } else {
                getStartingCoordinate(type, "Enter valid coordinate form! Example: A8");
            }
        } catch (NumberFormatException error) {
            getStartingCoordinate(type, "Enter valid coordinate form! Example: A8");
        }
        return new int[]{2, 1};
    }


    private Orientation defineOrientation(String input, ShipType type, Board board) {
        Orientation output = null;
        switch (input) {
            case "N":
                output = Orientation.NORTH;
                break;
            case "W":
                output = Orientation.WEST;
                break;
            case "S":
                output = Orientation.SOUTH;
                break;
            case "E":
                output = Orientation.EAST;
                break;
            default:
                placeShip(type, board);
        }
        return output;
    }
}
