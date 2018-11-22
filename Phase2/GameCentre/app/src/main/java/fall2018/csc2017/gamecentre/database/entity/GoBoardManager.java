package fall2018.csc2017.gamecentre.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import fall2018.csc2017.gamecentre.games.go.GoBoard;
import fall2018.csc2017.gamecentre.game.Board;
import fall2018.csc2017.gamecentre.game.BoardManager;

import static fall2018.csc2017.gamecentre.view.LoginActivity.myUser;

/**
 * Manages a Go Board by placing stones, checking for end-of-game conditions,
 * determining a winner, and handling taps.
 */
@Entity(tableName = "goBoards")
public class GoBoardManager extends BoardManager {
    // TODO: make this less garbage in formatting. Temporary.
    public String getOwner() {
        return owner;
    }

    public int getWhitePlayerAreaScore() {
        return whitePlayerAreaScore;
    }

    public int getBlackPlayerAreaScore() {
        return blackPlayerAreaScore;
    }

    /**
     * The owner of this specific game of Go.
     */
    @NonNull
    @PrimaryKey
    public String owner = myUser.getUsername();

    /** TODO: Type converter?
     * The Go board being managed.
     */
    @Ignore
    private GoBoard board;
    // TODO: make better formatting
    public void setWhitePlayerAreaScore(int whitePlayerAreaScore) {
        this.whitePlayerAreaScore = whitePlayerAreaScore;
    }

    public void setBlackPlayerAreaScore(int blackPlayerAreaScore) {
        this.blackPlayerAreaScore = blackPlayerAreaScore;
    }

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
    public GoBoardManager() { this(9, 9); }

    /**
     * Manage either a 13x13 or 19x19 Go board.
     *
     * @param numRows   The number of rows.
     * @param numCols   The number of columns.
     */
    public GoBoardManager(int numRows, int numCols) {

    }

    /**
     * Sets the board for this Go Board Manager.
     *
     * @param board The board to be managed.
     */
    public void setBoard(GoBoard board) { this.board = board; }

    @Override
    public GoBoard getBoard() { return board; }

    @Override
    public void touchMove(int position) {

    }

    @Override
    public void setBoard(Board board) {
        super.setBoard(board);
    }

    @Override
    public int getBoardScore() {
        return super.getBoardScore();
    }

    @Override
    public boolean isValidTap(int position) {
        return false;
    }

    /**
     * TODO: IMPLEMENT THIS
     * @return if game is over
     */
    @Override
    public boolean puzzleSolved() {
        return false;
    }

}
