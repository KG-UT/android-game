package fall2018.csc2017.gamecentre.scoreboard.scoreTests;

import org.junit.Test;

import fall2018.csc2017.gamecentre.ScoreSlidingTiles;
import fall2018.csc2017.gamecentre.ScoreTicTacToe;
import fall2018.csc2017.gamecentre.User;

import static org.junit.Assert.*;

public class scoreSlidingTilesTest extends scoreTest {

    /**
     * Setup scores in preparation for tests.
     */
    protected ScoreSlidingTiles setupScores() {
        User newUser = new User("1", "John");
        return new ScoreSlidingTiles(10, newUser);
    }

    /**
     * Test whether ScoreSlidingTiles initialization works.
     */
    @Test
    public void testScoreInitialization() {
        User newUser = new User("1", "John");
        ScoreSlidingTiles newScore1 = new ScoreSlidingTiles(1, 10, newUser.getUsername());
        ScoreSlidingTiles newScore2 = new ScoreSlidingTiles(10, newUser);

        assertEquals(1, newScore1.get_id());
        assertEquals(10, newScore1.getUserScore());
        assertEquals(newUser.getUsername(), newScore1.getUserName());

        assertEquals(1, newScore2.get_id());
        assertEquals(10, newScore2.getUserScore());
        assertEquals(newUser.getUsername(), newScore2.getUserName());
    }

    /**
     * Test whether method compareTo works with score numerical values.
     */
    @Test
    public void testCompareToNumbers() {
        User newUser1 = new User("1", "John");
        ScoreSlidingTiles newScore1 = new ScoreSlidingTiles(20, newUser1);
        ScoreSlidingTiles newScore2 = new ScoreSlidingTiles(15, newUser1);
        ScoreSlidingTiles newScore3 = new ScoreSlidingTiles(15, newUser1);

        assertEquals(1, newScore1.compareTo(newScore2));
        assertEquals(-1, newScore2.compareTo(newScore1));

        assertNotEquals(1, newScore2.compareTo(newScore3));
        assertNotEquals(-1, newScore2.compareTo(newScore3));
    }

    /**
     * Test whether method compareTo works with the names of the score holders.
     */
    @Test
    public void testCompareToNames() {
        User newUser1 = new User("1", "John");
        User newUser2 = new User("1", "Jane");
        ScoreSlidingTiles newScore1 = new ScoreSlidingTiles(15, newUser1);
        ScoreSlidingTiles newScore2 = new ScoreSlidingTiles(15, newUser1);
        ScoreSlidingTiles newScore3 = new ScoreSlidingTiles(15, newUser2);

        assertEquals(-1, newScore1.compareTo(newScore3));
        assertEquals(0, newScore1.compareTo(newScore2));
        assertEquals(1, newScore3.compareTo(newScore1));
    }

    /**
     * Test whether method equals works.
     */
    @Test
    public void testEquals() {
        User newUser1 = new User("1", "John");
        User newUser2 = new User("1", "Jane");
        ScoreSlidingTiles newScore1 = new ScoreSlidingTiles(15, newUser1);
        ScoreSlidingTiles newScore2 = new ScoreSlidingTiles(15, newUser1);
        ScoreSlidingTiles newScore3 = new ScoreSlidingTiles(15, newUser2);
        ScoreSlidingTiles newScore4 = new ScoreSlidingTiles(10, newUser2);
        ScoreTicTacToe newTicTacToeScore = new ScoreTicTacToe(15, newUser2);

        assertEquals(true, newScore1.equals(newScore2));
        assertEquals(false, newScore1.equals(null));
        assertEquals(false, newScore3.equals(newTicTacToeScore));
        assertEquals(false, newScore3.equals(newScore4));
    }
}
