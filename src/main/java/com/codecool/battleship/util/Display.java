package com.codecool.battleship.util;

import com.codecool.battleship.board.Board;
import com.codecool.battleship.board.SquareStatus;
import com.codecool.battleship.game.Player;
import com.codecool.battleship.ships.ShipType;

import java.util.ArrayList;

public class Display {

    private final String alphabetString = "abcdefghijklmnopqrstuvwxyz".toUpperCase();

    public void printMenu() {
        System.out.println(
                "Choose one of the menu points: \n" +
                        "1: Play game \n" +
                        "2: Quit \n");
    }

    public void askForCoordinates() {
        System.out.println(
                "Enter a coordinate: \n");
    }

    public void askForCoordinates(ShipType type) {
        String shipTypeName = type.getName();
        int shipLength = type.getSize();
        System.out.println(
                "You are placing a " + shipTypeName + " which is " + shipLength + " fields long. Enter the starting coordinate " +
                        "of your ship: \n");
    }

    public void printBoard(Board board, Player activePlayer) {
        StringBuilder output = new StringBuilder();
        String tableHeader = createTableHeader(board.getSize(), activePlayer);
        for (int row = 0; row < board.getSize(); row++) {
            output.append(row + 1);
            if (row + 1 < 10) output.append(" ");
            for (int column = 0; column < board.getSize(); column++) {
                if (board.getBoard()[row][column].getSquareStatus() == SquareStatus.EMPTY)
                    output.append("\033[1;34m|_").append("~").append("_");
                else {
                    if (board.getBoard()[row][column].getSquareStatus() == SquareStatus.SHIP) {
                        output.append("\033[1;34m|_").append("\033[1;32m#").append("\033[1;34m_");
                    } else if (board.getBoard()[row][column].getSquareStatus() == SquareStatus.HIT) {
                        output.append("\033[1;34m|_").append("\033[1;31mH").append("\033[1;34m_");
                    } else if (board.getBoard()[row][column].getSquareStatus() == SquareStatus.MISS) {
                        output.append("\033[1;34m|_").append("\033[0mM").append("\033[1;34m_");
                    }
                }
            }
            output.append("|\033[0m\n");
        }
        System.out.println(tableHeader);
        System.out.println(output);
    }

    public void printRadar(Board radar, Player activePlayer) {
        StringBuilder output = new StringBuilder();
        String tableHeader = createTableHeader(radar.getSize(), activePlayer);
        for (int row = 0; row < radar.getSize(); row++) {
            output.append(row + 1);
            if (row + 1 < 10) output.append(" ");
            for (int column = 0; column < radar.getSize(); column++) {
                if (radar.getBoard()[row][column].getSquareStatus() == SquareStatus.EMPTY)
                    output.append("\033[1;34m|_").append("~").append("_");
                else {
                    if (radar.getBoard()[row][column].getSquareStatus() == SquareStatus.SHIP) {
                        output.append("\033[1;34m|_").append("\033[1;32m#").append("\033[1;34m_");
                    } else if (radar.getBoard()[row][column].getSquareStatus() == SquareStatus.HIT) {
                        output.append("\033[1;34m|_").append("\033[1;31mH").append("\033[1;34m_");
                    } else if (radar.getBoard()[row][column].getSquareStatus() == SquareStatus.MISS) {
                        output.append("\033[1;34m|_").append("\033[1;33M").append("\033[1;34m_");
                    }
                }
            }
            output.append("|\033[0m\n");
        }
        System.out.println(tableHeader);
        System.out.println(output);
    }


    private String createTableHeader(int boardSize, Player activePlayer) {
        StringBuilder tableHeader = new StringBuilder().append("    ");
        for (int index = 0; index < boardSize; index++) {
            tableHeader.append(alphabetString.charAt(index)).append("   ");
        }
        return tableHeader.toString();
    }

    public void printGameplay() {
        System.out.println("");
    }

    public void printResults(Player activePlayer) {
        System.out.println(activePlayer.getName() + " has won!");
    }

    public void askForName() {
        System.out.println("Enter a name: \n");
    }

    public void turn(Player activePlayer) {
        System.out.println("It's " + activePlayer.getName() + "'s turn! Choose a coordinate to shoot at:\n");
    }

    public void askForOrientation(ArrayList<String> validOrientations) {
        StringBuilder output = new StringBuilder().append("Give me the orientation! (");
        for (String validOption : validOrientations)
            output.append(validOption).append(", ");
        System.out.println(output.substring(0, output.length() - 2) + ")");
    }

    public void wrongCoordinates(){
        System.out.println("Wrong coordinates, try again !");
    }

    public void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void askForBoardSize() {
        System.out.println("Choose a board size!");
    }

    public void askForEnter() {
        System.out.println("Press enter!");
    }

    public void deliverSizeErrorMessage() {
        System.out.println("Invalid size! The board size should be between 10 and 20!");
    }

    public void deliverInvalidOptionErrorMessage() {
        System.out.println("There is no option of your choice!");
    }

    public void printGoodByeMessage() {
        System.out.println("Good bye!");
    }

    public void printPlacementPhaseHeader(Player activePlayer) {
        System.out.println(activePlayer.getName() + "'s deployment phase:\n");
    }
}
