package fall2018.csc2017.gamecentre.scoreboard;

import org.junit.Test;

import fall2018.csc2017.gamecentre.ScoreSlidingTiles;
import fall2018.csc2017.gamecentre.User;

import static org.junit.Assert.*;

public class scoreSlidingTilesTest extends scoreTest {

    /**
     * Test whether method get_id works.
     */
    @Test
    public void testGet_id() {
        User newUser = new User(1, "Username", "Password");
        ScoreSlidingTiles newSlidingTilesScore = new ScoreSlidingTiles(10, newUser);

        assertEquals(1, newSlidingTilesScore.get_id());
    }

    /**
     * Test whether method set_id works.
     */
    @Test
    public void testSet_id() {
        User newUser = new User(1, "Username", "Password");
        ScoreSlidingTiles newSlidingTilesScore = new ScoreSlidingTiles(10, newUser);
        newSlidingTilesScore.set_id(2);

        assertEquals(2, newSlidingTilesScore.get_id());
    }

    /**
     * Test whether method getUserName works.
     */
    @Test
    public void testGetUserName() {
        User newUser = new User(1, "Username", "Password");
        ScoreSlidingTiles newSlidingTilesScore = new ScoreSlidingTiles(10, newUser);

        assertEquals("Username", newSlidingTilesScore.getUserName());
    }

    /**
     * Test whether method getUserScore works.
     */
    @Test
    public void testGetUserScore() {
        User newUser = new User(1, "Username", "Password");
        ScoreSlidingTiles newSlidingTilesScore = new ScoreSlidingTiles(10, newUser);

        assertEquals(10, newSlidingTilesScore.getUserScore());
    }

    /**
     * Test whether method getUserScore works.
     */
    @Test
    public void testToString() {
        User newUser = new User(1, "Username", "Password");
        ScoreSlidingTiles newSlidingTilesScore = new ScoreSlidingTiles(10, newUser);

        assertEquals("Username: 10", newSlidingTilesScore.toString());
    }

    /**
     * Test whether method compareTo works when the current value is larger than the to be compared
     * value.
     */
    @Test
    public void testCompareToLargerThan() {
        User newUser = new User(1, "Username", "Password");
        ScoreSlidingTiles newSlidingTilesScore1 = new ScoreSlidingTiles(15, newUser);
        ScoreSlidingTiles newSlidingTilesScore2 = new ScoreSlidingTiles(10, newUser);

        assertEquals(-1, newSlidingTilesScore1.compareTo(newSlidingTilesScore2));
    }

    /**
     * Test whether method compareTo works when the current value is equal to the to be compared
     * value.
     */
    @Test
    public void testCompareToEqual() {
        User newUser = new User(1, "Username", "Password");
        ScoreSlidingTiles newSlidingTilesScore1 = new ScoreSlidingTiles(10, newUser);
        ScoreSlidingTiles newSlidingTilesScore2 = new ScoreSlidingTiles(10, newUser);

        assertEquals(0, newSlidingTilesScore1.compareTo(newSlidingTilesScore2));
    }

    /**
     * Test whether method compareTo works when the current value is less than the to be compared
     * value.
     */
    @Test
    public void testCompareToLesserThan() {
        User newUser = new User(1, "Username", "Password");
        ScoreSlidingTiles newSlidingTilesScore1 = new ScoreSlidingTiles(10, newUser);
        ScoreSlidingTiles newSlidingTilesScore2 = new ScoreSlidingTiles(15, newUser);

        assertEquals(1, newSlidingTilesScore1.compareTo(newSlidingTilesScore2));
    }
}
