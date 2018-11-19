package fall2018.csc2017.gamecentre.Database.Entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import fall2018.csc2017.gamecentre.Board;
import fall2018.csc2017.gamecentre.BoardManager;
import fall2018.csc2017.gamecentre.Games.TicTacToe.TicTacToeBoard;

import static fall2018.csc2017.gamecentre.App.LoginActivity.myUser;

/**
 * Manage a board, including swapping tiles, checking for a win, and managing taps.
 */
@Entity(tableName = "TTTBoards")
public class TicTacToeBoardManager extends BoardManager {
    /**
     * The owner of this specific game of Tic Tac Toe.
     */
    @PrimaryKey
    private final String owner= myUser.getUsername();

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

    public void setBoard(TicTacToeBoard board) {
        this.board = board;
    }

    /**
     * Return the current board.
     */
    @Override
    public TicTacToeBoard getBoard() {
        return board;
    }
}
