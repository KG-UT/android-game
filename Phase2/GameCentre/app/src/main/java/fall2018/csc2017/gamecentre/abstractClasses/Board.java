package fall2018.csc2017.gamecentre.abstractClasses;

import android.support.annotation.NonNull;

import java.util.Observable;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * The items board.
 */
abstract public class Board extends Observable implements Serializable, Iterable<Object> {
    /**
     * The number of rows.
     */
    static protected int NUM_ROWS = 4;

    /**
     * The number of rows.
     */
    static protected int NUM_COLS = 4;

    /**
     * The items on the board in row-major order.
     */
    private Object[][] items;

    /**
     * A new board of items in row-major order.
     * Precondition: len(items) == NUM_ROWS * NUM_COLS
     *
     * @param rows the number of rows
     * @param cols the number of columns
     * @param items the items for the board
     */
    public Board(int rows, int cols, List<? extends Object> items){
        NUM_ROWS = rows;
        NUM_COLS = cols;
        this.items = new Object[NUM_ROWS][NUM_COLS];

        Iterator<? extends Object> iter = items.iterator();

        for (int row = 0; row != Board.NUM_ROWS; row++) {
            for (int col = 0; col != Board.NUM_COLS; col++) {
                this.items[row][col] = iter.next();
            }
        }
    }

    /**
     * Default constructor for firebase.
     */
    public Board() {}

    @NonNull
    @Override
    public Iterator<Object> iterator() {
        return new ItemsIterator();
    }

    private class ItemsIterator implements Iterator<Object> {
        /**
         * The current row on the board the iterator is on
         */
        int currentRow = 0;

        /**
         * The current column on the board the iterator is on
         */
        int currentCol = 0;

        /**
         * Return whether there is a next item for the iterator to go to
         * @return whether there is a next item for the iterator to go to
         */
        @Override
        public boolean hasNext() {
            return currentRow <= (NUM_ROWS - 1);
        }

        /**
         * Return the next item on the board
         * @return the next item on the board
         */
        @Override
        public Object next() {
            /*
            Increment the row.
            If the row is greater than the number of rows increment the column
             */
            if(currentRow <= NUM_ROWS - 1 && currentCol <= NUM_COLS - 1) {
                Object item = getItem(currentRow, currentCol);
                currentCol += 1;
                if(currentCol >= NUM_COLS) {
                    currentRow += 1;
                    currentCol = 0;
                }
                return item;
            } else {
                return null;
            }
        }
    }

    /**
     * Return the number of items on the board.
     * @return the number of items on the board
     */

    public int numObjects() {
        return NUM_COLS * NUM_ROWS;
    }

    /**
     * Return the number of columns of the board.
     * @return the number of columns of the board
     */
    public static int getNumCols() {
        return NUM_COLS;
    }

    /**
     * Return the number of rows of the board.
     * @return the number of rows of the board
     */
    public static int getNumRows() {
        return NUM_ROWS;
    }

    /**
     * return the number of tiles on the board.
     * @return the number of tiles on the board.
     */
    public static int numTiles(){ return NUM_COLS*NUM_ROWS;}
    /**
     * Return the item at (row, col)
     *
     * @param row the item row
     * @param col the item column
     * @return the item at (row, col)
     */
    public Object getItem(int row, int col) {
        return this.items[row][col];
    }

    /**
     * Set the item at (row, col)
     *
     * @param row the item row
     * @param col the item column
     * @param item the item to set at (row, col)
     */
    public void setItem(int row, int col, Object item) {
        this.items[row][col] = item;
    }

    /**
     * Get the items
     *
     * @return the items of the board
     */
    public Object[][] getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "Board{" +
                "items=" + Arrays.toString(items) +
                '}';
    }
}
