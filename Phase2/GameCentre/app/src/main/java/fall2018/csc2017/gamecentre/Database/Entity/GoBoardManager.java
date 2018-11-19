package fall2018.csc2017.gamecentre.Database.Entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import fall2018.csc2017.gamecentre.Board;
import fall2018.csc2017.gamecentre.BoardManager;
import fall2018.csc2017.gamecentre.Games.Go.GoBoard;

import static fall2018.csc2017.gamecentre.App.LoginActivity.myUser;

/**
 * Manages a Go Board by placing stones, checking for end-of-game conditions,
 * determining a winner, and handling taps.
 */
@Entity(tableName = "goBoards")
public class GoBoardManager extends BoardManager {
    /**
     * The owner of this specific game of Go.
     */
    @PrimaryKey
    private final String owner = myUser.getUsername();

    /**
     * The Go board being managed.
     */
    private GoBoard board;

    /**
     * The area score for the white player.
     */
    private int whitePlayerAreaScore = 0;

    /**
     * The area score for the black player.
     */
    private int blackPlayerAreaScore = 0;

    /**
     * Manage a pre-populated board.
     *
     * @param board the board
     */
    public GoBoardManager(Board board) { super(board); }

    /**
     * Manage a new 9 x 9 Go Board
     */
    public GoBoardManager() { super(9, 9); }

    /**
     * Manage either a 13x13 or 19x19 Go board.
     *
     * @param numRows   The number of rows.
     * @param numCols   The number of columns.
     */
    public GoBoardManager(int numRows, int numCols) { super(numRows, numCols); }

    /**
     * Sets the board for this Go Board Manager.
     *
     * @param board The board to be managed.
     */
    public void setBoard(GoBoard board) { this.board = board; }

    @Override
    public GoBoard getBoard() { return board; }



}
