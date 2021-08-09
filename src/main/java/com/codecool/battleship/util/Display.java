package com.codecool.battleship.util;

import com.codecool.battleship.ships.ShipType;

public class Display {
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
        String shipLength = type.getSize();
        System.out.println(
                "You are placing a" + shipTypeName + "which is" + shipLength + "long. Enter the starting coordinate:" +
                        "of your ship: \n");
    }

    public void printBoard() {
        System.out.println("");
    }

    public void printGameplay() {
        System.out.println("");
    }

    public void printResults() {
        System.out.println("");
    }

    public void askForName() {
        System.out.println("Enter a name: \n");
    }
}
