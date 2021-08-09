package com.codecool.battleship;

import com.codecool.battleship.game.Game;
import com.codecool.battleship.game.Player;
import com.codecool.battleship.util.Display;
import com.codecool.battleship.util.Input;

public class Battleship {
    public static void main(String[] args) {
        Display display = new Display();
        Input input = new Input();
        Game game = new Game();
        Player player1 = new Player();
        Player player2 = new Player();
    }

    public void gameLoop() {}
}
