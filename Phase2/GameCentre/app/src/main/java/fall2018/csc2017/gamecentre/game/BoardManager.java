package fall2018.csc2017.gamecentre.game;

import android.arch.persistence.room.Ignore;

import java.io.Serializable;
import java.util.Stack;


/**
 * Manage a board, including swapping tiles, checking for a win, and managing taps.
 */
abstract public class BoardManager implements Serializable {
    /** TODO: add type converter?
     * The board being managed.
     */
    @Ignore
    protected Board board;

    /** TODO: type converter?
     * A stack of moves made, for move reversals.
     */
    @Ignore
    private Stack<int[]> stackOfMoves = new Stack<>();

    // TODO:  idk, take a look later
    public void setScore(int score) {
        this.boardScore = score;
    }
    // TODO come back
    public void setStackOfMoves(Stack<int[]> stackOfMoves) {
        this.stackOfMoves = stackOfMoves;
    }

    public void setBoardScore(int boardScore) {
        this.boardScore = boardScore;
    }

    public void setUndosLeft(int undosLeft) {
        this.undosLeft = undosLeft;
    }

    /**
     * The score.
     */
    private int boardScore = 0;
    // TODO: temp
    public int getUndosLeft() {
        return undosLeft;
    }
    // TODO: come back
    public Stack<int[]> getStackOfMoves() {
        return stackOfMoves;
    }

    public int getBoardScore() {
        return boardScore;
    }

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
     * Increment the score
     *
     * @param amount amount to increment score by
     */
    public void incrementScore(int amount) {
        this.boardScore += amount;
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
