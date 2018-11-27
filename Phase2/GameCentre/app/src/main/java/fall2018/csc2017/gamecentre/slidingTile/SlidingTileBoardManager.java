package fall2018.csc2017.gamecentre.slidingTile;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.Random;

import fall2018.csc2017.gamecentre.game.Board;
import fall2018.csc2017.gamecentre.game.BoardManager;
import fall2018.csc2017.gamecentre.Tile;

/**
 * Manage a board, including swapping tiles, checking for a win, and managing taps.
 */
public class SlidingTileBoardManager extends BoardManager {
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
     * Manage a new 4 by 4 shuffled board
     */
    SlidingTileBoardManager() {
        this(4, 4);
    }

    /**
     * Manage a board that has been pre-populated.
     * @param board the board
     */
    public SlidingTileBoardManager(SlidingTileBoard board) {
        super(board);
    }

    /**
     * Manage a new shuffled board.
     */
    SlidingTileBoardManager(int numRows, int numCols) {
        List<Tile> tiles = new ArrayList<>();
        final int numTiles = numRows * numCols;
        for (int tileNum = 1; tileNum != numTiles; tileNum++) {
            tiles.add(new Tile(tileNum));
        }
        tiles.add(new Tile(Tile.BLANK_ID));

        setBoard(new SlidingTileBoard(numRows, numCols, tiles));
        shuffle();
    }

    /**
     * Return the current board.
     */
    public SlidingTileBoard getBoard() {
        return (SlidingTileBoard) super.getBoard();
    }

    /**
     * Return whether the tiles are in row-major order.
     *
     * @return whether the tiles are in row-major order
     */
    public boolean puzzleSolved() {
        boolean solved = true;
        int previousTileId = 0;

        SlidingTileBoard board = getBoard();

        Iterator<Object> boardIterator = board.iterator();
        for(int i = 0; i < board.numTiles() - 1; i++) {
            Tile currentTile = (Tile) boardIterator.next();
            int currentTileId = currentTile.getId();
            if(currentTileId - previousTileId != 1) {
                solved = false;
            }
            previousTileId = currentTileId;
        }

        return solved;
    }

    /**
     * Return an array representing the row and column of the nearest blank space
     * to a tile (if any)
     *
     * @param row The row of the current tile
     * @param col The col of the current tile
     * @return An array representing the row and col of the nearest blank space
     */
    private int[] nearestBlank(int row, int col) {
        int blankId = Tile.BLANK_ID;
        SlidingTileBoard board = getBoard();

        Tile above = row == 0 ? null : board.getTile(row - 1, col);
        Tile below = row == Board.getNumRows() - 1 ? null : board.getTile(row + 1, col);
        Tile left = col == 0 ? null : board.getTile(row, col - 1);
        Tile right = col == Board.getNumCols() - 1 ? null : board.getTile(row, col + 1);

        if (below != null && below.getId() == blankId) {
            return new int[] {row + 1, col};
        } else if (above != null && above.getId() == blankId) {
            return new int[] {row - 1, col};
        }  else if (left != null && left.getId() == blankId) {
            return new int[] {row, col - 1};
        } else if (right != null && right.getId() == blankId) {
            return new int[] {row, col + 1};
        } else {
            return null;
        }
    }
    /* A shuffling algorithm to scramble the board.
     *
     * @param tiles The tiles to be shuffled.
     */
    private void shuffle(){
        int NUM_RANDOM_MOVES = 30;
        for (int i=0; i < NUM_RANDOM_MOVES; i++){
            makeRandomMove();
        }
    }

    /* From the valid moves available, makes a random move.
     *
     */
    private void makeRandomMove(){
        ArrayList<Integer> validMoves = getValidMoves();
        Random randomNumGenerator = new Random();
        int moveIndex = randomNumGenerator.nextInt(validMoves.size());
        hiddenMove(validMoves.get(moveIndex));
    }

    /* Get an ArrayList of valid moves.
     *
     */
    private ArrayList<Integer> getValidMoves(){
        ArrayList<Integer> validMoves = new ArrayList<>();
        for (int position=0; position<getBoard().numTiles(); position++){
            if (isValidTap(position)){
                validMoves.add(position);
            }
        }
        return validMoves;
    }

    /**
     * Return whether any of the four surrounding tiles is the blank tile.
     *
     * @param position the tile to check
     * @return whether the tile at position is surrounded by a blank tile
     */
    public boolean isValidTap(int position) {
        int row = position / Board.getNumCols();
        int col = position % Board.getNumRows();
        return nearestBlank(row, col) != null;
    }

    /**
     * Process a touch at position in the board, swapping tiles as appropriate.
     *
     * @param position the position
     */
    public void touchMove(int position) {
        int row = position / Board.getNumRows();
        int col = position % Board.getNumCols();

        int[] blankLocation = nearestBlank(row, col);
        if(blankLocation != null) {
            hiddenMove(position);
            stackOfMoves.add(blankLocation);
            score += 1;
        }
    }
    /*
     * The computer performs a move, so it doesnt not affect player score, nor get recorded
     * for undos.
     *
     * @param position the position
     */
    private void hiddenMove(int position) {
        int row = position / Board.getNumRows();
        int col = position % Board.getNumCols();

        int[] blankLocation = nearestBlank(row, col);
        if (blankLocation != null) {
            int blankRow = blankLocation[0];
            int blankCol = blankLocation[1];
            getBoard().swapTiles(blankRow, blankCol, row, col);
        }
    }

    /**
     * Check if an undo can be performed. In other words, if there is a move to undo.
     *
     * @return a boolean showing if there are moves in the stack of moves.
     */
   public boolean canUndo(){
        return !stackOfMoves.isEmpty();
    }

    /**
     * Reverses the last move made, aka, the move at the top of the moves stack.
     *
     * PRECONDITION: THE MOVE STACK IS NOT EMPTY
     */

    public void undoMove() {
        int[] backPosition = stackOfMoves.pop();
        int row = backPosition[0];
        int col = backPosition[1];
        int[] blankLocation = nearestBlank(row, col);
        if (blankLocation != null && undosLeft != 0) {
            int blankRow = blankLocation[0];
            int blankCol = blankLocation[1];
            getBoard().swapTiles(blankRow, blankCol, row, col);
            undosLeft -= 1;
        }
    }

    /**
     * Getter function for the score.
     *
     * @return the score associated with this board.
     */
   public int getScore() { return score;}

   /**
    * Getter function for the Undos left.
    * @return the number of undos the player has left.
    */
   int getUndosLeft() {
       if (undosLeft >= 0){
           return undosLeft;
       } else{
           return 99999999;
       }
   }

//   /**
//    * Set undos as as some value.
//    * PRECONDITION: i >= 0
//    */
//   public void setUndos(int i){
//       undosLeft = i;
//   }
//
//   /**
//    * Give player unlimited Undos.
//    */
//   public void setUnlimitedUndos(){
//       undosLeft = -1;
//   };
}
