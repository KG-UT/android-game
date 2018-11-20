package fall2018.csc2017.gamecentre.ticTacToe;

import java.util.List;

import fall2018.csc2017.gamecentre.game.Board;
import fall2018.csc2017.gamecentre.Tile;

/**
 * The tic tac toe board.
 */
public class TicTacToeBoard extends Board {
    TicTacToeBoard(int rows, int cols, List<TicTacToeTile> tiles){
        super(rows, cols, tiles);
    }

    /**
     * Return the tile at (row, col)
     *
     * @param row the tile row
     * @param col the tile column
     * @return the tile at (row, col)
     */
    public TicTacToeTile getTile(int row, int col) {
        return (TicTacToeTile) getItem(row, col);
    }

    /**
     * Set the tile at (row, col)
     *
     * @param row the tile row
     * @param col the tile column
     * @param tile the tile to set at (row, col)
     */
    public void setTile(int row, int col, TicTacToeTile tile) {
        setItem(row, col, tile);
    }

    /**
     * Update the tile at (row, col)
     */
    public void updateTile(int row, int col, String newState) {
        ((TicTacToeTile) getItem(row, col)).setState(newState);

        setChanged();
        notifyObservers();
    }

    /**
     * Reset the board
     */
    public void reset() {
        for(int i = 0; i < getNumRows(); i++) {
            for(int j = 0; j < getNumCols(); j++) {
                setTile(i, j, new TicTacToeTile());
            }
        }

        setChanged();
        notifyObservers();
    }


}
