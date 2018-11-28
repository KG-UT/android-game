package fall2018.csc2017.gamecentre.boardManagers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import fall2018.csc2017.gamecentre.games.ticTacToe.TicTacToeBoard;
import fall2018.csc2017.gamecentre.games.ticTacToe.TicTacToeTile;
import fall2018.csc2017.gamecentre.abstractClasses.Board;
import fall2018.csc2017.gamecentre.abstractClasses.BoardManager;

import static fall2018.csc2017.gamecentre.view.LoginActivity.currentUser;

/**
 * Manage a board, including swapping tiles, checking for a win, and managing taps.
 */
public class TicTacToeBoardManager extends BoardManager {
    /**
     * The owner's key value.
     */
    private final String ownerKeyValue = currentUser.getUid();

    /**
     * Default constructor for Firebase to use.
     */
    public TicTacToeBoardManager() {}

    /**
     * Manage a new 3 by 3 tic tac toe board
     */
    public TicTacToeBoardManager(int numRows, int numCols) {
        List<TicTacToeTile> tiles = new ArrayList<>();
        final int numTiles = numRows * numCols;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles.add(new TicTacToeTile());
        }

        setBoard(new TicTacToeBoard(numRows, numCols, tiles));
    }

    /**
     * Manage a board that has been pre-populated.
     * @param board the board
     */
    public TicTacToeBoardManager(Board board) {
        super(board);
    }

    /**
     * Return all blank locations on the tic tac toe board
     *
     * @return All blank locations on the tic tac toe board
     */
    public ArrayList<Integer[]> getAllBlanks() {
        ArrayList<Integer[]> blankLocations = new ArrayList<>();
        Iterator<Object> iterator = getBoard().iterator();

        for(int i = 0; i < board.numObjects() - 1; i++) {
            TicTacToeTile tile = (TicTacToeTile) iterator.next();
            if(tile.getState().equals(TicTacToeTile.BLANK)) {
                int row = i / Board.getNumRows();
                int col = i % Board.getNumCols();
                blankLocations.add(new Integer[]{row, col});
            }
        }

        return blankLocations;
    }

    /**
     * Make a random tic tac toe move if possible
     */
    public void makeRandomMove() {
        ArrayList<Integer[]> blankLocations = getAllBlanks();

        if(blankLocations.size() > 0) {
            // Adapted from: https://stackoverflow.com/questions/5034370/retrieving-a-random-item-from-arraylist
            Random randomGenerator = new Random();
            int index = randomGenerator.nextInt(blankLocations.size());
            Integer[] position = blankLocations.get(index);

            makeMove(position[0], position[1], TicTacToeTile.O);
        }
    }

    /**
     * Make a move at row, col, with state
     *
     * @param row the row to make the move
     * @param col the col to make the move
     * @param state the new state to be set
     */
    public void makeMove(int row, int col, String state) {
        getBoard().updateTile(row, col, state);
    }

    @Override
    public void touchMove(int position) {
        int row = position / Board.getNumRows();
        int col = position % Board.getNumCols();

        makeMove(row, col, TicTacToeTile.X);
        makeRandomMove();
    }

    @Override
    public TicTacToeBoard getBoard() {
        return (TicTacToeBoard) board;
    }

    @Override
    public boolean isValidTap(int position) {
        return true;
    }

    /**
     * Return whether the row has the same states
     *
     * @param rowNum The row to examine
     * @return whether the row has the same states
     */
    public boolean rowSame(int rowNum) {
        TicTacToeBoard board = getBoard();
        String state = board.getTile(rowNum, 0).getState();
        if(state == TicTacToeTile.BLANK) {
            return false;
        }

        for(int i = 0; i < Board.getNumCols(); i++) {
            if(!board.getTile(rowNum, i).getState().equals(state)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Return whether the col has the same states
     *
     * @param colNum the col to examine
     * @return whether the col has the same states
     */
    public boolean colSame(int colNum) {
        TicTacToeBoard board = getBoard();
        String state = board.getTile(0, colNum).getState();
        if(state == TicTacToeTile.BLANK) {
            return false;
        }
        for(int i = 0; i < Board.getNumCols(); i++) {
            if(!board.getTile(i, colNum).getState().equals(state)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean puzzleSolved() {
        for(int i = 0; i < Board.getNumRows(); i++) {
            if(this.rowSame(i)) {
                return true;
            }
        }

        for(int i = 0; i < Board.getNumCols(); i++) {
            if(this.colSame(i)) {
                return true;
            }
        }

        return false;
    }

}
