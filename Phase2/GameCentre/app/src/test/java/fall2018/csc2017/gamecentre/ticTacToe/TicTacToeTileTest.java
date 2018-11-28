package fall2018.csc2017.gamecentre.games.ticTacToe;

import org.junit.Test;
import static org.junit.Assert.*;

public class TicTacToeTileTest {
    /**
     * Test get state
     */
    @Test
    public void testGetAndSetState() {
        TicTacToeTile tile = new TicTacToeTile();
        assertEquals(tile.getState(), TicTacToeTile.BLANK);
        tile.setState(TicTacToeTile.X);
        assertEquals(tile.getState(), TicTacToeTile.X);
    }
}
