package fall2018.csc2017.gamecentre;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import fall2018.csc2017.gamecentre.boardManagers.SlidingTileBoardManager;
import static org.junit.Assert.*;
import fall2018.csc2017.gamecentre.games.slidingTile.SlidingTileBoard;


import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class BoardAndTileTest {

    /** The board manager for testing. */
    private SlidingTileBoardManager slidingTileBoardManager;

    /**
     * Make a set of tiles that are in order.
     * @return a set of tiles that are in order
     */
    private List<Tile> makeTiles() {
        List<Tile> tiles = new ArrayList<>();
       // final int numTiles = boardManager.getBoard().getNumRows() * boardManager.getBoard().getNumCols();
        final int numTiles = 4*4;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles.add(new Tile(tileNum + 1, tileNum));
        }

        return tiles;
    }

    /**
     * Make a solved Board.
     */
    private void setUpCorrect() {
        List<Tile> tiles = makeTiles();
        SlidingTileBoard board = new SlidingTileBoard(4,4,tiles);
        slidingTileBoardManager = new SlidingTileBoardManager(board);
    }

    /**
     * Shuffle a few tiles.
     */
    private void swapFirstTwoTiles() {
        slidingTileBoardManager.getBoard().swapTiles(0, 0, 0, 1);
    }

    /**
     * Test whether swapping two tiles makes a solved board unsolved.
     */
    @Test
    public void testIsSolved() {
        setUpCorrect();

        assertTrue(slidingTileBoardManager.puzzleSolved());
        swapFirstTwoTiles();
        assertFalse(slidingTileBoardManager.puzzleSolved());
    }

    /**
     * Test whether swapping the first two tiles works.
     */
    @Test
    public void testSwapFirstTwo() {
        setUpCorrect();
        assertEquals(1, slidingTileBoardManager.getBoard().getTile(0, 0).getId());
        assertEquals(2, slidingTileBoardManager.getBoard().getTile(0, 1).getId());

        slidingTileBoardManager.getBoard().swapTiles(0, 0, 0, 1);

        assertEquals(2, slidingTileBoardManager.getBoard().getTile(0, 0).getId());
        assertEquals(1, slidingTileBoardManager.getBoard().getTile(0, 1).getId());
    }

    /**
     * Test whether swapping the last two tiles works.
     */
    @Test
    public void testSwapLastTwo() {
        setUpCorrect();

        assertEquals(15, slidingTileBoardManager.getBoard().getTile(3, 2).getId());
        assertEquals(16, slidingTileBoardManager.getBoard().getTile(3, 3).getId());

        slidingTileBoardManager.getBoard().swapTiles(3, 3, 3, 2);

        assertEquals(16, slidingTileBoardManager.getBoard().getTile(3, 2).getId());
        assertEquals(15, slidingTileBoardManager.getBoard().getTile(3, 3).getId());
    }

    /**
     * Test whether isValidHelp works.
     */
    @Test
    public void testIsValidTap() {
        setUpCorrect();

        assertTrue(slidingTileBoardManager.isValidTap(11));
        assertTrue(slidingTileBoardManager.isValidTap(15));
        assertTrue(slidingTileBoardManager.isValidTap(10));
    }
}

