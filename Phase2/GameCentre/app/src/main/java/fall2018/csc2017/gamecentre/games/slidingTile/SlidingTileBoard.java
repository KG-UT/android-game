package fall2018.csc2017.gamecentre.games.slidingTile;

import java.util.Arrays;
import java.util.List;

import fall2018.csc2017.gamecentre.Tile;
import fall2018.csc2017.gamecentre.abstractClasses.Board;

/**
 * The sliding tiles board.
 */
public class SlidingTileBoard extends Board {
    /**
     * A new board of tiles in row-major order.
     * Precondition: len(tiles) == NUM_ROWS * NUM_COLS
     *
     * @param rows the number of rows
     * @param cols the number of columns
     * @param tiles the tiles for the board
     */
    public SlidingTileBoard(int rows, int cols, List<Tile> tiles){
        super(rows, cols, tiles);
    }

    /**
     * Return the number of tiles on the board.
     * @return the number of tiles on the board
     */
    public int numTiles() {
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
     * Return the tile at (row, col)
     *
     * @param row the tile row
     * @param col the tile column
     * @return the tile at (row, col)
     */
    public Tile getTile(int row, int col) {
        return (Tile) getItem(row, col);
    }

    /**
     * Set the tile at (row, col)
     *
     * @param row the tile row
     * @param col the tile column
     * @param tile the tile to set at (row, col)
     */
    public void setTile(int row, int col, Tile tile) {
        setItem(row, col, tile);
    }

    /**
     * Swap the tiles at (row1, col1) and (row2, col2)
     *
     * @param row1 the first tile row
     * @param col1 the first tile col
     * @param row2 the second tile row
     * @param col2 the second tile col
     */
    public void swapTiles(int row1, int col1, int row2, int col2) {
        Tile tmp = getTile(row1, col1);

        setTile(row1, col1, getTile(row2, col2));
        setTile(row2, col2, tmp);

        setChanged();
        notifyObservers();
    }

    @Override
    public String toString() {
        return "Board{" +
                "tiles=" + Arrays.toString(getItems()) +
                '}';
    }
}
