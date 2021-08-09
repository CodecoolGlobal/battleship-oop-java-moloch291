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
        Player player1 = new Player("player1");
        Player player2 = new Player("player2");
        Board board1 = new Player1Board(10);
        Board board2 = new Player2Board(10);
        board1.printDetailedOcean(10);
        board2.printDetailedOcean(10);
    }

    public void gameLoop() {}
}
