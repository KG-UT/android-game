package fall2018.csc2017.gamecentre.MatchingCards;

import org.junit.Test;
import fall2018.csc2017.gamecentre.games.matchingCards.MatchingCardsTile;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MatchingCardsTileTest {
    /**
     * Create a new MatchingCardsTile.
     */
    public MatchingCardsTile createMatchingCardsTile(){
        return new MatchingCardsTile(2);
    }

    /**
     * Test if isFaceUp is working
     */
    @Test
    public void testIsFaceUp(){
        MatchingCardsTile tile = createMatchingCardsTile();
        assertFalse(tile.isFaceUp());
    }

    /**
     * Test if setFaceUp is working
     */
    @Test
    public void testSetFaceUp(){
        MatchingCardsTile tile = createMatchingCardsTile();
        tile.setFaceUp();
        assertTrue(tile.isFaceUp());
    }

    /**
     * Test if setFaceDown is working
     */
    @Test
    public void testSetFaceDown(){
        MatchingCardsTile tile = createMatchingCardsTile();
        tile.setFaceUp();
        tile.setFaceDown();
        assertFalse(tile.isFaceUp());
    }

    /**
     * Test if getNumber is working
     */
    @Test
    public void testGetNumber(){
        MatchingCardsTile tile = createMatchingCardsTile();
        assertEquals(tile.getNumber(), 2);
    }

    /**
     * Test if isEqual is working
     */
    @Test
    public void testIsEqual(){
        MatchingCardsTile tile1 = createMatchingCardsTile();
        MatchingCardsTile tile2 = createMatchingCardsTile();
        assertTrue(tile1.isEqual(tile2));
    }
}
