package fall2018.csc2017.gamecentre.Games.TicTacToe;

import java.util.List;

import fall2018.csc2017.gamecentre.Board;
import fall2018.csc2017.gamecentre.Tile;

/**
 * The Tic Tac Toe board.
 */
public class TicTacToeBoard extends Board {
    TicTacToeBoard(int rows, int cols, List<Tile> tiles){
        super(rows, cols, tiles);
    }
}
