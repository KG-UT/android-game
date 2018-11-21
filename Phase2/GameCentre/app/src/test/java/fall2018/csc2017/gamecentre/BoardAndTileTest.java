package fall2018.csc2017.gamecentre;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD
import fall2018.csc2017.gamecentre.Database.Entity.SlidingTileBoardManager;
||||||| merged common ancestors
import static org.junit.Assert.*;
=======
import fall2018.csc2017.gamecentre.game.Board;
import fall2018.csc2017.gamecentre.slidingTile.SlidingTileBoardManager;

import static org.junit.Assert.*;
>>>>>>> master

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class BoardAndTileTest {

    /** The board manager for testing. */
<<<<<<< HEAD
    SlidingTileBoardManager slidingTileBoardManager;
||||||| merged common ancestors
    BoardManager boardManager;
=======
    SlidingTileBoardManager boardManager;
>>>>>>> master

    /**
     * Make a set of tiles that are in order.
     * @return a set of tiles that are in order
     */
    private List<Tile> makeTiles() {
        List<Tile> tiles = new ArrayList<>();
        final int numTiles = Board.NUM_ROWS * Board.NUM_COLS;
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
        Board board = new Board(tiles);
<<<<<<< HEAD
        slidingTileBoardManager = new SlidingTileBoardManager(board);
||||||| merged common ancestors
        boardManager = new BoardManager(board);
=======
        boardManager = new SlidingTileBoardManager(board);
>>>>>>> master
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
        assertEquals(true, slidingTileBoardManager.puzzleSolved());
        swapFirstTwoTiles();
        assertEquals(false, slidingTileBoardManager.puzzleSolved());
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
        assertEquals(true, slidingTileBoardManager.isValidTap(11));
        assertEquals(true, slidingTileBoardManager.isValidTap(15));
        assertEquals(false, slidingTileBoardManager.isValidTap(10));
    }
}

