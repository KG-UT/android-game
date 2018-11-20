package fall2018.csc2017.gamecentre.game;

import java.io.Serializable;
import java.util.Stack;


/**
 * Manage a board, including swapping tiles, checking for a win, and managing taps.
 */
abstract public class BoardManager implements Serializable {
    /**
     * The board being managed.
     */
    protected Board board;

    /**
     * A stack of moves made, for move reversals.
     */
    private Stack<int[]> stackOfMoves = new Stack<>();

    /**
     * The score.
     */
    private int score = 0;

    /**
     * The number of undos left.
     */
    private int undosLeft = 3;

    /**
     * A boardManager without an initial board
     */
    public BoardManager() {}

    /**
     * Manage a board that has been pre-populated.
     * @param board the board
     */
    public BoardManager(Board board) {
        this.board = board;
    }

    /**
     * Return the current board.
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Set the current board.
     */
    public void setBoard(Board board) { this.board = board; }

    /**
     * Process a touch at position in the board.
     *
     * @param position the position
     */
    abstract public void touchMove(int position);

    /**
     * Getter function for the score.
     *
     * @return the score associated with this board.
     */
    public int getScore() { return score;}

    /**
     * Setter function for the score
     *
     */

    /**
     * Increment the score
     *
     * @param amount amount to increment score by
     */
    public void incrementScore(int amount) {
        this.score += amount;
    }


    /**
     * Return whether or not the puzzle is solved or game is over
     *
     * @return whether or not the puzzle is solved or game is over
     */
   abstract public boolean puzzleSolved();

    /**
     * Return whether or not a tap is valid
     *
     * @param position the position of the tap
     * @return whether or not a tap is valid
     */
    abstract public boolean isValidTap(int position);


}
