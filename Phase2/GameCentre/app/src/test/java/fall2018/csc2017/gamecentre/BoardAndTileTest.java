package fall2018.csc2017.gamecentre;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import fall2018.csc2017.gamecentre.game.Board;
import fall2018.csc2017.gamecentre.games.slidingTile.SlidingTileBoardManager;
import fall2018.csc2017.gamecentre.games.slidingTile.*;

import org.mockito.ArgumentMatchers;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class BoardAndTileTest {

    /** The board manager for testing. */
    private SlidingTileBoardManager boardManager;

    /**
     * Make a set of tiles that are in order.
     * @return a set of tiles that are in order
     */
    private List<Tile> makeTiles() {
        List<Tile> tiles = new ArrayList<>();
       // final int numTiles = boardManager.getBoard().getNumRows() * boardManager.getBoard().getNumCols();
        final int numTiles = 4*4;
        for (int tileNum = 0; tileNum < numTiles - 1; tileNum++) {
            tiles.add(new Tile(tileNum + 1, tileNum));
        }
        tiles.add(new Tile(Tile.BLANK_ID, 15));
        return tiles;
    }

    /**
     * Make a solved Board.
     */
    private void setUpCorrect() {
        List<Tile> tiles = makeTiles();
//        Board board = new SlidingTileBoard(tiles);
//        boardManager = new SlidingTileBoardManager(board);
        SlidingTileBoard board = new SlidingTileBoard(4,4,tiles);
        boardManager = new SlidingTileBoardManager(board);
    }

    /**
     * Shuffle a few tiles.
     */
    private void swapFirstTwoTiles() {
        boardManager.getBoard().swapTiles(0, 0, 0, 1);
    }

    /**
     * Test whether swapping two tiles makes a solved board unsolved.
     */
    @Test
    public void testIsSolved() {
        setUpCorrect();
        assertEquals(true, boardManager.puzzleSolved());
        swapFirstTwoTiles();
        assertEquals(false, boardManager.puzzleSolved());
    }

    /**
     * Test whether swapping the first two tiles works.
     */
    @Test
    public void testSwapFirstTwo() {
        setUpCorrect();
        assertEquals(1, boardManager.getBoard().getTile(0, 0).getId());
        assertEquals(2, boardManager.getBoard().getTile(0, 1).getId());
        boardManager.getBoard().swapTiles(0, 0, 0, 1);
        assertEquals(2, boardManager.getBoard().getTile(0, 0).getId());
        assertEquals(1, boardManager.getBoard().getTile(0, 1).getId());
    }

    /**
     * Test whether swapping the last two tiles works.
     */
    @Test
    public void testSwapLastTwo() {
        setUpCorrect();
        assertEquals(15, boardManager.getBoard().getTile(3, 2).getId());
        //we are always setting blankId to be 9999, since we cant
        assertEquals(9999, boardManager.getBoard().getTile(3, 3).getId());
        boardManager.getBoard().swapTiles(3, 3, 3, 2);
        assertEquals(9999, boardManager.getBoard().getTile(3, 2).getId());
        assertEquals(15, boardManager.getBoard().getTile(3, 3).getId());
    }

    /**
     * Test whether isValidHelp works.
     */

    @Test
    public void testIsValidTap() {
        setUpCorrect();
        assertEquals(true, boardManager.isValidTap(11));
        assertEquals(true, boardManager.isValidTap(14));
        assertEquals(false, boardManager.isValidTap(10));
    }

    /**
     * Test whether touchMove() works, and that score updates
     */
    @Test
    public void testTouchMove() {
        setUpCorrect();
        boardManager.touchMove(14);
        assertEquals(1,boardManager.getScore());
    }

    /**
     * Test whether canUndo() works
     */
    @Test
    public void testcanUndo(){
        setUpCorrect();
        boardManager.touchMove(11);
        assertTrue(boardManager.canUndo());
    }

    /**
     * Test whether undoMove() works
     */
    @Test
    public void testUndoMove() {
        setUpCorrect();
        boardManager.touchMove(11);
        assertEquals(9999, boardManager.getBoard().getTile(2, 3).getId());
        boardManager.undoMove();
        assertEquals(9999, boardManager.getBoard().getTile(3, 3).getId());


    }

    /**
     * Test whether number of Undo's are updating correctly
     */
    @Test
    public void testUndosLeft()
    { setUpCorrect();
        boardManager.touchMove(11);
        boardManager.touchMove(10);
        assertEquals(9999, boardManager.getBoard().getTile(2, 2).getId());
        boardManager.undoMove();
        boardManager.undoMove();
        assertEquals(9999, boardManager.getBoard().getTile(3, 3).getId());
        assertEquals(1,boardManager.getUndosLeft());

    }

    /**
     * tests shuffle function
     */
    @Test
    public void testshuffle() {
        setUpCorrect();
        assertTrue(boardManager.puzzleSolved());
        SlidingTileBoardManager bm = new SlidingTileBoardManager(5,5);
        assertFalse(bm.puzzleSolved());
    }



}

