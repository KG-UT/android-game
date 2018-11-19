package fall2018.csc2017.gamecentre.Go;

/**
 * The class representing the black and white stone playing pieces in Go.
 */
public class Stone {
    /**
     * The colour of the stone.
     * Black = "Black"
     * White = "White"
     */
    private final String colour;
    /**
     * The position of a given stone
     */
    private final int coordinate;

    Stone(String colour, int coordinate) {
        this.colour = colour;
        this.coordinate = coordinate;
    }

    /**
     * Gets the colour of the stone.
     *
     * @return the colour of the stone.
     */
    public String getColour() {
        return colour;
    }

    /**
     * Gets the numeric board position of a stone.
     *
     * @return the position of a stone.
     */
    public int getCoordinate() {
        return coordinate;
    }
}
