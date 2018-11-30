package fall2018.csc2017.gamecentre.MatchingCards;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import fall2018.csc2017.gamecentre.games.matchingCards.MatchingCardsBoard;
import fall2018.csc2017.gamecentre.games.matchingCards.MatchingCardsTile;

public class MatchingCardsBoardTest {

    /**
     * Creates the tiles
     */
    public List<MatchingCardsTile> createMatchingCardsTiles(){
        List<MatchingCardsTile> tiles = new ArrayList<>();
        final int numTiles = 4 * 4;
        for (int tileNum = 0; tileNum != numTiles/2; tileNum++) {
            tiles.add(new MatchingCardsTile(tileNum));
            tiles.add(new MatchingCardsTile(tileNum));
        }
        return tiles;
    }

    /**
     * Test if flipCardUp is working
     */
    @Test
    public void testFlipCardUp(){
        List<MatchingCardsTile> tiles = createMatchingCardsTiles();
        MatchingCardsBoard board = new MatchingCardsBoard(4,4, tiles);
        board.flipCardUp(0,0);
        assertTrue(tiles.get(0).isFaceUp());
    }

    /**
     * test if getCard is working
     */
    @Test
    public void testGetCard(){
        List<MatchingCardsTile> tiles = createMatchingCardsTiles();
        MatchingCardsBoard board = new MatchingCardsBoard(4,4, tiles);
        MatchingCardsTile tile = board.getCard(0,0);
        assertEquals(tile, tiles.get(0));
    }

    /**
     * Test if twoTempCardsAreUp is working
     */
    @Test
    public void testTwoTempCardsAreUp(){
        List<MatchingCardsTile> tiles = createMatchingCardsTiles();
        MatchingCardsBoard board = new MatchingCardsBoard(4,4, tiles);
        board.flipCardUp(0,0);
        assertFalse(board.twoTempCardsAreUp());
        board.flipCardUp(0,1);
        assertTrue(board.twoTempCardsAreUp());
    }

    /**
     * Test if flipTempCardsDown is working
     */
    @Test
    public void testflipTempCardsDown(){
        List<MatchingCardsTile> tiles = createMatchingCardsTiles();
        MatchingCardsBoard board = new MatchingCardsBoard(4,4, tiles);
        board.flipCardUp(0,0);
        board.flipCardUp(0,1);
        board.flipTempCardsDown();
        assertFalse(board.twoTempCardsAreUp());
    }

    /**
     * Test if getTempFaceUpCards is working
     */
    @Test
    public void testGetTempFaceUpCards(){
        List<MatchingCardsTile> tiles = createMatchingCardsTiles();
        MatchingCardsBoard board = new MatchingCardsBoard(4,4, tiles);
        board.flipCardUp(0,0);
        board.flipCardUp(0, 1);
        List<MatchingCardsTile> tempTiles = board.getTempFaceupCards();
        for (int i=0; i!= tempTiles.size(); i++){
            assertEquals(tempTiles.get(i), tiles.get(i));
        }
    }

    /**
     * Test if clearTempFaceUpCards is working
     */
    @Test
    public void testClearTempFaceUpCards(){
        List<MatchingCardsTile> tiles = createMatchingCardsTiles();
        MatchingCardsBoard board = new MatchingCardsBoard(4,4, tiles);
        board.flipCardUp(0,0);
        board.clearTempFaceUpCards();
        List<MatchingCardsTile> tempTiles = board.getTempFaceupCards();
        assertEquals(tempTiles.size(), 0);
    }

}
