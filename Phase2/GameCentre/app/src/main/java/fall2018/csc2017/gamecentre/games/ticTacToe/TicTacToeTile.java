package fall2018.csc2017.gamecentre.games.ticTacToe;

import java.io.Serializable;

/**
 * A Tile in a TicTacToe Game.
 */
public class TicTacToeTile implements Serializable {
    /**
     * Possible states
     */
    public static final String X = "X";
    public static final String O = "O";
    public static final String BLANK = "";

    /**
     * The current state of the title
     */
    private String state;

    /**
     * Return the number to display for the tile
     *
     * @return number to display for the tile
     */
    public String getState() {
        return state;
    }

    /**
     * Set the state of the tile
     *
     * @param state the state to set (must be "X", "O", or "")
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * A Tile with id and background. The background may not have a corresponding image.
     */
    public TicTacToeTile() {
        this.state = BLANK;
    }
}