package fall2018.csc2017.gamecentre.TicTacToe;

import android.media.Image;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import fall2018.csc2017.gamecentre.Board;
import fall2018.csc2017.gamecentre.BoardManager;
import fall2018.csc2017.gamecentre.TicTacToe.TicTacToeBoard;
import fall2018.csc2017.gamecentre.Tile;

/**
 * Manage a board, including swapping tiles, checking for a win, and managing taps.
 */
class TicTacToeBoardManager extends BoardManager {
    /**
     * The board being managed.
     */
    private TicTacToeBoard board;

    /**
     * Manage a new 4 by 4 shuffled board
     */
    public TicTacToeBoardManager() {
        super(4, 4);
    }
    /**
     * Manage a board that has been pre-populated.
     * @param board the board
     */
    public TicTacToeBoardManager(Board board) {
        super(board);
    }

    /**
     * Manage a new shuffled board.
     */
    public TicTacToeBoardManager(int numRows, int numCols) {
        super(numRows, numCols);
    }

    /**
     * Return the current board.
     */
    @Override
    public TicTacToeBoard getBoard() {
        return board;
    }
}
