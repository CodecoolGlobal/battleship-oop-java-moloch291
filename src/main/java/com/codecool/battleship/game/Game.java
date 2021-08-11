package com.codecool.battleship.game;

import com.codecool.battleship.Battleship;
import com.codecool.battleship.board.*;
import com.codecool.battleship.ships.Ship;
import com.codecool.battleship.ships.ShipType;
import com.codecool.battleship.util.Display;
import com.codecool.battleship.util.Input;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private final Display display;
    private final Input input;
    private final Player1Board player1Board;
    private final Player2Board player2Board;
    private final Player1Radar player1Radar;
    private final Player2Radar player2Radar;
    private final Player player1;
    private final Player player2;

    public Game(int size) {

        this.display = new Display();
        this.input = new Input();

        this.player1Board = new Player1Board(size);
        this.player2Board = new Player2Board(size);
        this.player1Radar = new Player1Radar(size);
        this.player2Radar = new Player2Radar(size);

        display.clearConsole();
        display.askForName();
        this.player1 = new Player(input.askForName(), this, player1Board);
        display.clearConsole();
        display.askForName();
        this.player2 = new Player("\033[0;35m" + input.askForName() + "\033[0m", this, player2Board);
    }

    public void gameLoop() {
        int currentRound = 1;
        boolean isRunning = true;
        while (isRunning) {
            Player activePlayer = currentRound % 2 == 0 ? player2 : player1;
            Player opponent = activePlayer == player1 ? player2 : player1;
            Board activeBoard = activePlayer == player1 ? player2Board : player1Board;
            Board activeRadar = activePlayer == player1 ? player2Radar : player1Radar;
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
        prepareRound(activePlayer, radar);
        String shootArea = input.inputCoordinate();
        int[] shootCoordinates = input.toCoordinates(shootArea);
        Square square = board.getBoard()[shootCoordinates[0]][shootCoordinates[1]];
        Square radarSquare = radar.getBoard()[shootCoordinates[0]][shootCoordinates[1]];
        SquareStatus status = square.getSquareStatus();
        switch (status) {
            case EMPTY:
                radarSquare.setSquareStatus(SquareStatus.MISS);
                break;
            case SHIP:
                hitShip(opponent, shootCoordinates, radarSquare);
                break;
        }
        display.printRadar(radar, activePlayer);
    }

    private void prepareRound(Player activePlayer, Board radar) {
        display.turn(activePlayer);
        display.printRadar(radar, activePlayer);
    }

    private void hitShip(Player opponent, int[] shootCoordinates, Square radarSquare) {
        if (radarSquare.getSquareStatus() == SquareStatus.HIT) {
            System.out.println("You've already hit that!");
        } else {
            radarSquare.setSquareStatus(SquareStatus.HIT);
            damageEnemyShip(opponent.getShips(), shootCoordinates);
            lookForSunkShips(opponent.getShips());
            opponent.setShipSquares();
        }
    }

    private void lookForSunkShips(List<Ship> enemyShips) {
        for (Ship ship : enemyShips) {
            int damagedTiles = 0;
            for (Square tile : ship.getPlacement()) {
                if (tile.getSquareStatus() == SquareStatus.HIT)
                    damagedTiles++;
            }
            if (damagedTiles == ship.getPlacement().size()) {
                display.deliverSunkMessage(ship.getType().getName());
            }
        }
    }

    private void damageEnemyShip(List<Ship> enemyShips, int[] shootCoordinates) {
        for (Ship ship : enemyShips) {
            for (Square coordinate : ship.getPlacement()) {
                if (coordinate.getX() == shootCoordinates[0] &&
                        coordinate.getY() == shootCoordinates[1]) {
                    coordinate.setSquareStatus(SquareStatus.HIT);
                }
            }
        }
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
        positionList.add(new Square(shipNosePosition[0], shipNosePosition[1], SquareStatus.SHIP));
        return positionList;
    }

    private List<Square> getOrientation(
            ShipType type,
            Board board,
            List<Square> positionList,
            int[] shipNosePosition
    ) {
        ArrayList<String> validOrientations = validOrientations(shipNosePosition, type, board);
        Orientation shipOriented = getShipOrientation(type, board, validOrientations);
        shipOriented = validateOrientation(type, board, validOrientations, shipOriented);
        positionList.add(new Square(shipNosePosition[0], shipNosePosition[1], SquareStatus.SHIP));
        fillUpPositionList(type, positionList, shipNosePosition, shipOriented);
        return positionList;
    }

    private Orientation validateOrientation(
            ShipType type,
            Board board,
            ArrayList<String> validOrientations,
            Orientation shipOriented
    ) {
        try {
        while (!validOrientations.contains(shipOriented.getName())) {
            display.wrongCoordinates();
            shipOriented = getShipOrientation(type, board, validOrientations);
        }} catch (NullPointerException error) {
            display.wrongCoordinates();
            shipOriented = getShipOrientation(type, board, validOrientations);
        }
        return shipOriented;
    }

    private int[] validateNosePosition(ShipType type, Board board, int[] shipNosePosition) {
        while (!input.inputValidation(board, input.toString(shipNosePosition))) {
            shipNosePosition = getStartingCoordinate(type, "Enter valid direction!");
        }
        return shipNosePosition;
    }

    private ArrayList<String> validOrientations(int[] shipNosePosition, ShipType type, Board board) {
        ArrayList<String> validDirection = new ArrayList<>();

        if (shipNosePosition[0] - type.getSize() >= 0 &&
                noShipInTheWay(type, Orientation.NORTH, board, shipNosePosition)) {
            validDirection.add("N");
        }
        if (shipNosePosition[1] - type.getSize() >= 0 &&
                noShipInTheWay(type, Orientation.WEST,  board, shipNosePosition)) {
            validDirection.add("W");
        }
        if (shipNosePosition[0] + type.getSize() <= board.getSize() &&
                noShipInTheWay(type, Orientation.SOUTH, board, shipNosePosition)) {
            validDirection.add("S");
        }
        if (shipNosePosition[1] + type.getSize() <= board.getSize() &&
                noShipInTheWay(type, Orientation.EAST, board, shipNosePosition)) {
            validDirection.add("E");
        }
        return validDirection;
    }

    private boolean noShipInTheWay(ShipType type, Orientation oriented, Board board, int[] shipNosePosition) {
        for (int step = 1; step <= type.getSize(); step++) {
            if (ifInBounds(oriented, board, shipNosePosition, step)) {
                if (board.getBoard()
                        [shipNosePosition[0] + oriented.getX() * step][shipNosePosition[1] + oriented.getY() * step]
                            .getSquareStatus() == SquareStatus.SHIP) {
                    return false;
                }
            }

        }
        return true;
    }

    private boolean ifInBounds(Orientation oriented, Board board, int[] shipNosePosition, int step) {
        return shipNosePosition[0] + oriented.getX() * step >= 0 &&
                shipNosePosition[0] + oriented.getX() * step < board.getSize() &&
                shipNosePosition[1] + oriented.getY() * step >= 0 &&
                shipNosePosition[1] + oriented.getY() * step < board.getSize();
    }

    private void fillUpPositionList(
            ShipType type,
            List<Square> positionList,
            int[] shipNosePosition,
            Orientation shipOriented
    ) {
        int multiplierForShip = 1;
        for (int addition = 0; addition < type.getSize() - 1; addition++) {
            positionList.add(
                    new Square(shipNosePosition[0] + shipOriented.getX() * multiplierForShip,
                            shipNosePosition[1] + shipOriented.getY() * multiplierForShip,
                            SquareStatus.SHIP
                    ));
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
