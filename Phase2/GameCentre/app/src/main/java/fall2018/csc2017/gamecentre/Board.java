package fall2018.csc2017.gamecentre;

import android.support.annotation.NonNull;

import java.util.Observable;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * The sliding tiles board.
 */
public class Board extends Observable implements Serializable, Iterable<Tile> {

    /**
     * The number of rows.
     */
    static int NUM_ROWS = 4;

    /**
     * The number of rows.
     */
    static int NUM_COLS = 4;

    /**
     * The tiles on the board in row-major order.
     */
    private Tile[][] tiles;

    /**
     * A new board of tiles in row-major order.
     * Precondition: len(tiles) == NUM_ROWS * NUM_COLS
     *
     * @param rows the number of rows
     * @param cols the number of columns
     * @param tiles the tiles for the board
     */

    Board(int rows, int cols, List<Tile> tiles){
        NUM_ROWS = rows;
        NUM_COLS = cols;
        this.tiles = new Tile[NUM_ROWS][NUM_COLS];

        Iterator<Tile> iter = tiles.iterator();

        for (int row = 0; row != Board.NUM_ROWS; row++) {
            for (int col = 0; col != Board.NUM_COLS; col++) {
                this.tiles[row][col] = iter.next();
            }
        }

    }

    @NonNull
    @Override
    public Iterator<Tile> iterator() {
        return new TileIterator();
    }

    private class TileIterator implements Iterator<Tile> {
        /**
         * The current row on the board the iterator is on
         */
        int currentRow = 0;

        /**
         * The current column on the board the iterator is on
         */
        int currentCol = 0;

        /**
         * Return whether there is a next tile for the iterator to go to
         * @return whether there is a next tile for the iterator to go to
         */
        @Override
        public boolean hasNext() {
            return currentRow <= (NUM_ROWS - 1);
        }

        /**
         * Return the next tile on the board
         * @return the next tile on the board
         */
        @Override
        public Tile next() {
            /*
            Increment the row.
            If the row is greater than the number of rows increment the column
             */
            if(currentRow <= NUM_ROWS - 1 && currentCol <= NUM_COLS - 1) {
                Tile tile = getTile(currentRow, currentCol);
                currentCol += 1;
                if(currentCol >= NUM_COLS) {
                    currentRow += 1;
                    currentCol = 0;
                }
                return tile;
            } else {
                return null;
            }
        }
    }

    /**
     * Return the number of tiles on the board.
     * @return the number of tiles on the board
     */
    int numTiles() {
        return NUM_COLS * NUM_ROWS;
    }

    /**
     * Return the tile at (row, col)
     *
     * @param row the tile row
     * @param col the tile column
     * @return the tile at (row, col)
     */
    Tile getTile(int row, int col) {
        return tiles[row][col];
    }

    /**
     * Swap the tiles at (row1, col1) and (row2, col2)
     *
     * @param row1 the first tile row
     * @param col1 the first tile col
     * @param row2 the second tile row
     * @param col2 the second tile col
     */
    void swapTiles(int row1, int col1, int row2, int col2) {
        Tile tmp = tiles[row1][col1];
        tiles[row1][col1] = tiles[row2][col2];
        tiles[row2][col2] = tmp;

        setChanged();
        notifyObservers();
    }

    @Override
    public String toString() {
        return "Board{" +
                "tiles=" + Arrays.toString(tiles) +
                '}';
    }
}
