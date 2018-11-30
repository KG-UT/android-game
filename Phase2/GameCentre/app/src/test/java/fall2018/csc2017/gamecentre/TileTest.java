package fall2018.csc2017.gamecentre;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests for Tile.
 */
public class TileTest {

    /**
     * Test whether Tile initialization works.
     */
    @Test
    public void testTileInitialization() {
        Tile newTile1 = new Tile(5, 10);
        Tile newTile2 = new Tile(20);

        assertEquals(5, newTile1.getId());
        assertEquals(10, newTile1.getBackground());

        assertEquals(20, newTile2.getId());
        assertEquals(0, newTile2.getBackground());
    }
}
