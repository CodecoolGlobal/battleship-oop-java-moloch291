package com.codecool.battleship.util;

import java.util.Scanner;

public class Input {
    Scanner input = new Scanner(System.in);

    public int inputForMenu() {
        return 0;
    }

    public String inputCoordinate() {
        return "";
    }

    private boolean inputValidation() {
        return false;
    }

    @Override
    public String toString() {
        return "";
    }

    public int[] toCoordinates(String s) {
        return null;
    }

    public String askForName() {
        return "";
    }
}
