package com.codecool.battleship;

import com.codecool.battleship.game.Game;
import com.codecool.battleship.util.Display;
import com.codecool.battleship.util.Input;

public class Battleship {

    public static void main(String[] args) {
        mainMenu(1);
    }

    private static void mainMenu(int mode) {
        Display display = new Display();
        Input input = new Input();
        display.clearConsole();
        deliverErrorMessages(display, mode);
        display.printMenu();
        int menuInput = input.inputForMenu();
        evaluateInput(display, input, menuInput);
    }

    private static void evaluateInput(Display display,
                                      Input input,
                                      int menuInput) {
        switch (menuInput) {
            case 1:
                loadGame(display, input);
                break;
            case 2:
                display.clearConsole();
                display.printGoodByeMessage();
                System.exit(0);
            default:
                mainMenu(3);
        }
    }

    private static void deliverErrorMessages(Display display, int mode) {
        switch (mode) {
            case 2:
                display.deliverSizeErrorMessage();
                break;
            case 3:
                display.deliverInvalidOptionErrorMessage();
                break;
        }
    }

    private static void loadGame(Display display, Input input) {
        display.clearConsole();
        display.askForBoardSize();
        int chosenSize = input.inputForMenu();
        if (chosenSize >= 10 && chosenSize <= 20) {
            Game game = new Game(chosenSize);
            game.gameLoop();
        }
        else
            mainMenu(2);
    }


}
