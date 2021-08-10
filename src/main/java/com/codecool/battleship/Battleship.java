package com.codecool.battleship;

import com.codecool.battleship.board.Board;
import com.codecool.battleship.board.Player1Board;
import com.codecool.battleship.board.Player2Board;
import com.codecool.battleship.game.Game;
import com.codecool.battleship.game.Player;
import com.codecool.battleship.util.Display;
import com.codecool.battleship.util.Input;

public class Battleship {
    public static void main(String[] args) {
        Display display = new Display();
        Input input = new Input();
        Game game = new Game();

        display.askForName();
        Player player1 = new Player(input.askForName(), game);
        display.askForName();
        Player player2 = new Player(input.askForName(), game);
    }

    public void gameLoop() {}
}
