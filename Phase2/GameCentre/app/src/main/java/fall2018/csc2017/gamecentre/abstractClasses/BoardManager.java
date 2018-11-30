package fall2018.csc2017.gamecentre.abstractClasses;


import java.io.Serializable;
import java.util.Stack;
import static fall2018.csc2017.gamecentre.view.LoginActivity.currentUser;



/**
 * Manage a board, including swapping tiles, checking for a win, and managing taps.
 */
abstract public class BoardManager implements Serializable {

    public String getOwnerKeyValue() {
        return ownerKeyValue;
    }

    public void setOwnerKeyValue() {
        if (currentUser == null) {
            this.ownerKeyValue = "TEST";
        }
        else {
            this.ownerKeyValue = currentUser.getEmail();

        }
    }

    protected String ownerKeyValue;



    /**
     * The board being managed.
     */
    protected Board board;

    /**
     * The stack of ints that define a series of moves.
     */
    private Stack<int[]> stackOfMoves = new Stack<>();

    /**
     * The number of undos left.
     */
    private int undosLeft = 3;

    /**
     * Sets the new value of stackOfMoves (from class).
     *
     * @param stackOfMoves the new stack value of stackOfMoves (from class)
     */
    public void setStackOfMoves(Stack<int[]> stackOfMoves) {
        this.stackOfMoves = stackOfMoves;
    }

    /**
     * Sets the new value of boardScore (from class).
     *
     * @param boardScore the new int value of stackOfMoves (from class)
     */
    public void setBoardScore(int boardScore) {
        this.boardScore = boardScore;
    }

    /**
     * Sets the new value of undosLeft (from class).
     *
     * @param undosLeft the new int value of undosLeft (from class)
     */
    public void setUndosLeft(int undosLeft) {
        this.undosLeft = undosLeft;
    }

    /**
     * The score.
     */
    private int boardScore = 0;

    /**
     * Returns the amount of undos left.
     *
     * @return the int value of the amount of undos left
     */
    public int getUndosLeft() {
        return undosLeft;
    }

    /**
     * Returns the stack of moves.
     *
     * @return the stack of ints defined by stackOfMoves
     */
    public Stack<int[]> getStackOfMoves() {
        return stackOfMoves;
    }

    /**
     * Return the amount of undos left.
     *
     * @return the int value of the amount of undos left
     */
    public int getBoardScore() {
        return boardScore;
    }

    /**
     * A boardManager without an initial board
     */
    public BoardManager() {}

    /**
     * Manage a board that has been pre-populated.
     *
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
    public int getScore() {
        return this.boardScore;
    }

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
