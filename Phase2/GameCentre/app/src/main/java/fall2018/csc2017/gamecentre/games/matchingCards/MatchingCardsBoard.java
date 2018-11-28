package fall2018.csc2017.gamecentre.games.matchingCards;

import fall2018.csc2017.gamecentre.game.Board;
import java.util.List;

public class MatchingCardsBoard extends Board {

    /**
     * A new board of tiles in row-major order.
     * Precondition: len(tiles) == NUM_ROWS * NUM_COLS
     *
     * @param rows the number of rows
     * @param cols the number of columns
     * @param tiles the tiles for the board
     */
    public MatchingCardsBoard(int rows, int cols, List<MatchingCardsTile> tiles){
        super(rows, cols, tiles);
    }


}
