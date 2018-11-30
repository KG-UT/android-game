package fall2018.csc2017.gamecentre.games.MatchingCards;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import fall2018.csc2017.gamecentre.game.Board;
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
        final int numTiles = Board.getNumRows() * Board.getNumCols();
        for (int tileNum = 0; tileNum != numTiles / 2; tileNum++) {
            tiles.add(new MatchingCardsTile(tileNum));
            tiles.add(new MatchingCardsTile(tileNum));
        }
        return tiles;
    }


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
        setUp();
        MatchingCardsBoard board = boardManager.getBoard();
        assertFalse(board.getCard(0,0).isFaceUp());
        boardManager.touchMove(0);
        assertTrue(board.getCard(0,0).isFaceUp());
    }



}
