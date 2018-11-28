package fall2018.csc2017.gamecentre.games.ticTacToe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import fall2018.csc2017.gamecentre.game.Board;
import fall2018.csc2017.gamecentre.game.BoardManager;

/**
 * Manage a tic tac toe board.
 * Game is based on how many times the user can beat a randomly playing computer
 * before the user loses.
 */
class TicTacToeBoardManager extends BoardManager {
    /**
     * The default string to represent no same states (X or O) for a row, col or diagonal
     */
    private String NO_SAME = "";
    /**
     * The default string to represent no winner
     */
    private String NO_WINNER = "";
    /**
     * The state for the current player
     */
    private String CURRENT_PLAYER = TicTacToeTile.X;
    /**
     * The state for the computer
     */
    private String COMPUTER_PLAYER = TicTacToeTile.O;
    /**
     * Number of columns of the tic tac toe board
     */
    private int NUM_COLS;
    /**
     * Number of rows of the tic tac toe board
     */
    private int NUM_ROWS;
    /**
     * The score (how many times the user beat or tied the computer)
     */
    private int score = 0;

    /**
     * Manage a new 3 by 3 tic tac toe board
     */
    public TicTacToeBoardManager(int numRows, int numCols) {
        NUM_COLS = numCols;
        NUM_ROWS = numRows;
        List<TicTacToeTile> tiles = new ArrayList<>();
        final int numTiles = NUM_COLS * NUM_ROWS;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles.add(new TicTacToeTile());
        }
        setBoard(new TicTacToeBoard(NUM_ROWS, NUM_COLS, tiles));
    }

    /**
     * Return all blank locations on the tic tac toe board
     *
     * @return All blank locations on the tic tac toe board
     */
    public ArrayList<Integer[]> getAllBlanks() {
        ArrayList<Integer[]> blankLocations = new ArrayList<>();
        Iterator<Object> iterator = getBoard().iterator();

        for(int i = 0; i < board.numObjects(); i++) {
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
    public void computerMakeMove() {
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

        makeMove(row, col, CURRENT_PLAYER);
        String winner = getWinner();
        if(winner.equals(NO_WINNER)) {
            if (getAllBlanks().size() == 0) {
                getBoard().reset();
                this.incrementScore(1);
            } else {
                computerMakeMove();
            }
        } else if(winner.equals(CURRENT_PLAYER)) {
            getBoard().reset();
            this.incrementScore(1);
        }
    }

    @Override
    public TicTacToeBoard getBoard() {
        return (TicTacToeBoard) board;
    }

    @Override
    public boolean isValidTap(int position) {
        int row = position / Board.getNumRows();
        int col = position % Board.getNumCols();

        return getBoard().getTile(row, col).getState().equals(TicTacToeTile.BLANK);
    }

    /**
     * Check whether the row has the same states and return it if any
     *
     * @param rowNum The row to examine
     * @return the state that is the same on the row
     */
    public String rowSame(int rowNum) {
        TicTacToeBoard board = getBoard();
        String state = board.getTile(rowNum, 0).getState();
        if(state == TicTacToeTile.BLANK) {
            return NO_SAME;
        }
        for(int i = 0; i < Board.getNumCols(); i++) {
            if(!board.getTile(rowNum, i).getState().equals(state)) {
                return NO_SAME;
            }
        }
        return state;
    }

    /**
     * Check whether the column has the same states and return it if any
     *
     * @param colNum the col to examine
     * @return the state that is the same on the col (otherwise return NO_SAME)
     */
    public String colSame(int colNum) {
        TicTacToeBoard board = getBoard();
        String state = board.getTile(0, colNum).getState();
        if(state == TicTacToeTile.BLANK) {
            return NO_SAME;
        }
        for(int i = 0; i < Board.getNumRows(); i++) {
            if(!board.getTile(i, colNum).getState().equals(state)) {
                return NO_SAME;
            }
        }
        return state;
    }

    /**
     * Check whether the diagonal (from top left to bottom right)
     * has the same state and return the state if any
     *
     * @return the state that is the same on the diagonal (otherwise return NO_SAME)
     */
    public String leftRightDiagonalSame() {
        TicTacToeBoard board = getBoard();
        String state = board.getTile(board.getNumRows()/2, board.getNumCols()/2).getState();
        if(state == TicTacToeTile.BLANK) {
            return NO_SAME;
        }
        for(int i = 0; i < Board.getNumCols(); i++) {
            if(!board.getTile(i, i).getState().equals(state)) {
                return NO_SAME;
            }
        }
        return state;
    }

    /**
     * Check whether the diagonal from bottom left to top right has the same state and return
     * the state if any
     *
     * @return the state that is the same on the diagonal (otherwise return NO_SAME)
     */
    public String rightLeftDiagonalSame() {
        TicTacToeBoard board = getBoard();
        String state = board.getTile(board.getNumRows()/2, board.getNumCols()/2).getState();
        if(state == TicTacToeTile.BLANK) {
            return NO_SAME;
        }
        for(int i = 0; i < Board.getNumCols(); i++) {
            if(!board.getTile( i, Board.getNumCols() - i - 1).getState().equals(state)) {
                return NO_SAME;
            }
        }
        return state;
    }

    /**
     * Check whether any of the rows of the board contain the same state
     *
     * @return the state that is the same on any of the rows if it exists (otherwise returns NO_SAME)
     */
    public String rowsSame() {
        for(int i = 0; i < Board.getNumRows(); i++) {
            if(!this.rowSame(i).equals(NO_SAME)) {
                return this.rowSame(i);
            }
        }
        return NO_SAME;
    }

    /**
     * Check whether any of the cols of the board contain the same state
     *
     * @return the state that is the same on any of the cols if it exists (otherwise returns NO_SAME)
     */
    public String colsSame() {
        for(int i = 0; i < Board.getNumCols(); i++) {
            if(!this.colSame(i).equals(NO_SAME)) {
                return this.colSame(i);
            }
        }
        return NO_SAME;
    }

    /**
     * Return the winner of the game if any otherwise return NO_WINNER
     * @return
     */
    public String getWinner() {
        if(!this.rowsSame().equals(NO_SAME)) {
            return this.rowsSame();
        }
        if(!this.colsSame().equals(NO_SAME)) {
            return this.colsSame();
        }
        if(!this.rightLeftDiagonalSame().equals(NO_SAME)) {
            return this.rightLeftDiagonalSame();
        }
        if(!this.leftRightDiagonalSame().equals(NO_SAME)) {
            return this.leftRightDiagonalSame();
        }
        return NO_WINNER;
    }

    @Override
    /**
     * Puzzle is "solved", when the user gets beaten by the computer.
     */
    public boolean puzzleSolved() {
        if(this.getWinner().equals(COMPUTER_PLAYER)){
            return true;
        } else {
            return false;
        }
    }

}
