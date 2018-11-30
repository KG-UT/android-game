package fall2018.csc2017.gamecentre.MatchingCards;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import fall2018.csc2017.gamecentre.games.matchingCards.MatchingCardsBoard;
import fall2018.csc2017.gamecentre.games.matchingCards.MatchingCardsBoardManager;
import fall2018.csc2017.gamecentre.games.matchingCards.MatchingCardsTile;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class MatchingCardsBoardManagerTest {
    /** The board manager for testing. */
    private MatchingCardsBoardManager boardManager;

    /**
     * Make a set of tiles that are in order.
     * @return a set of tiles that are in order
     */
    private List<MatchingCardsTile> makeTiles() {
        List<MatchingCardsTile> tiles = new ArrayList<>();
        final int numTiles = MatchingCardsBoard.getNumRows() * MatchingCardsBoard.getNumCols();
        for (int tileNum = 0; tileNum != numTiles / 2; tileNum++) {
            tiles.add(new MatchingCardsTile(tileNum));
            tiles.add(new MatchingCardsTile(tileNum));
        }
        return tiles;
    }

    @Before
    public void setUp(){
        List<MatchingCardsTile> tiles = makeTiles();
        MatchingCardsBoard board = new MatchingCardsBoard(4,4, tiles);
        boardManager = new MatchingCardsBoardManager(board);
    }

    /**
     * Test whether touchMove works
     */
    @Test
    public void testTouchMove(){
        MatchingCardsBoard board = boardManager.getBoard();
        assertFalse(board.getCard(0,0).isFaceUp());
        boardManager.touchMove(0);
        assertTrue(board.getCard(0,0).isFaceUp());
    }

    /**
     * Test whether puzzleSolved works correctly
     */

    @Test
    public void testPuzzleSolved(){
        assertTrue(boardManager.puzzleSolved());
        MatchingCardsBoardManager temp = new MatchingCardsBoardManager(4,4);
        //created a shuffled board
        temp.touchMove(3);
        assertFalse(temp.puzzleSolved());
    }

    /**
     * Test whether isValidTap works
     */
    @Test
    public void testisValidTap(){
        assertTrue(boardManager.isValidTap(5));
        boardManager.touchMove(3);
        assertFalse(boardManager.isValidTap(3));
    }

    /**
     *  Test whether getScore works correctly
     */
    @Test
    public void testgetScore(){
        MatchingCardsBoardManager temp1 = new MatchingCardsBoardManager();
        temp1.touchMove(3);
        temp1.touchMove(5);
        assertEquals(2,temp1.getScore());
    }
}
