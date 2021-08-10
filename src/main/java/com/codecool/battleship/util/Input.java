package com.codecool.battleship.util;

import com.codecool.battleship.board.Board;
import com.codecool.battleship.board.Square;
import com.codecool.battleship.board.SquareStatus;

import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Input {

    private final String alphabetString = "abcdefghijklmnopqrstuvwxyz".toUpperCase();

    public int inputForMenu() {
        Scanner input = new Scanner(System.in);
        return input.nextInt();
    }

    public String inputCoordinate() {
        Scanner input = new Scanner(System.in);
        return input.nextLine().toUpperCase();
    }

    private boolean inputValidation(Board board, String stringCoordinate) {
        // 0 = row, 1 = column
        if (validateCoordinateInput(board, stringCoordinate, 0) ||
            validateCoordinateInput(board, stringCoordinate, 1) ||
            validateCoordinateStatus(board, stringCoordinate)) {
            return false;
        } else {
            return true;
        }
    }

    private boolean validateCoordinateInput(Board board, String stringCoordinate, int rowOrColumnDetector) {
        return toCoordinates(stringCoordinate)[rowOrColumnDetector] > board.getSize();
    }

    private boolean validateCoordinateStatus(Board board, String stringCoordinate) {
        int[] shootCoordinate = toCoordinates(stringCoordinate);
        Square square = board.getBoard()[shootCoordinate[0]][shootCoordinate[1]];
        return square.getSquareStatus() != SquareStatus.MISS || square.getSquareStatus() != SquareStatus.HIT;
    }

    public String toString(int[] intArrayCoordinate) {
        String coordinate;
        int row = intArrayCoordinate[0] + 1;
        int column = intArrayCoordinate[1];
        String columnLetter= String.valueOf(alphabetString.charAt(column));
        String rowNumber = String.valueOf(row);
        coordinate = columnLetter + rowNumber;
        return coordinate;
    }

    public int[] toCoordinates(String stringCoordinate) {
        int row = Integer.parseInt((stringCoordinate.substring(1)))-1;
        int columnNumber = alphabetString.indexOf(stringCoordinate.charAt(0));
        System.out.println(Arrays.toString(new int[]{row, columnNumber}));
        return new int[] {row, columnNumber};
    }

    public String askForName() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

}
