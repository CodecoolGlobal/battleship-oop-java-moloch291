package com.codecool.battleship;

import com.codecool.battleship.game.Game;
import com.codecool.battleship.util.Display;
import com.codecool.battleship.util.Input;

public class Battleship {


    public static void main(String[] args) {
        mainMenu();
    }

    private static void mainMenu() {
        Display display = new Display();
        Input input = new Input();
        Game game = new Game();
        display.clearConsole();
        display.printMenu();
        int menuInput = input.inputForMenu();

        switch (menuInput) {
            case 1:
                display.askForBoardSize();
                int chosenSize = input.inputForMenu();
                if (chosenSize >= 10 && chosenSize <= 20)
                    game.gameLoop(chosenSize);
                else
                    mainMenu();
                break;
            case 2:
                System.exit(0);
            default:
                mainMenu();
        }
    }


}
