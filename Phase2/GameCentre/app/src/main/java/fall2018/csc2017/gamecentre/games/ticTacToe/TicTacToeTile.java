package fall2018.csc2017.gamecentre.games.ticTacToe;

import java.io.Serializable;

/**
 * A Tile in a sliding tiles puzzle.
 */
public class TicTacToeTile implements Serializable {
    /**
     * Possible states
     */
    public static String X = "X";
    public static String O = "O";
    public static String BLANK = "";

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
//        if(state != X || state != O || state != BLANK) {
//            throw InvalidParameterException;
//        }
        this.state = state;
    }

    /**
     * A Tile with id and background. The background may not have a corresponding image.
     */
    public TicTacToeTile() {
        this.state = BLANK;
    }
}