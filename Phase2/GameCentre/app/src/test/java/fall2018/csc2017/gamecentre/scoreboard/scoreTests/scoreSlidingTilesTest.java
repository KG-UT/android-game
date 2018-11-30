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
        User newUser = new User(1, "John", "Password");
        ScoreSlidingTiles newScoreSlidingScore1 = new ScoreSlidingTiles(1, 10, newUser.getUsername());
        ScoreSlidingTiles newScoreSlidingScore2 = new ScoreSlidingTiles(10, newUser);

        assertEquals(1, newScoreSlidingScore1.get_id());
        assertEquals(10, newScoreSlidingScore1.getUserScore());
        assertEquals(newUser.getUsername(), newScoreSlidingScore1.getUserName());

        assertEquals(1, newScoreSlidingScore2.get_id());
        assertEquals(10, newScoreSlidingScore2.getUserScore());
        assertEquals(newUser.getUsername(), newScoreSlidingScore2.getUserName());
    }

    /**
     * Test whether method compareTo works with score numerical values.
     */
    @Test
    public void testCompareToNumbers() {
        User newUser1 = new User("1", "John");
        ScoreSlidingTiles newSlidingTilesScore1 = new ScoreSlidingTiles(20, newUser1);
        ScoreSlidingTiles newSlidingTilesScore2 = new ScoreSlidingTiles(15, newUser1);
        ScoreSlidingTiles newSlidingTilesScore3 = new ScoreSlidingTiles(15, newUser1);

        assertEquals(1, newSlidingTilesScore1.compareTo(newSlidingTilesScore2));
        assertEquals(-1, newSlidingTilesScore2.compareTo(newSlidingTilesScore1));

        assertNotEquals(1, newSlidingTilesScore2.compareTo(newSlidingTilesScore3));
        assertNotEquals(-1, newSlidingTilesScore2.compareTo(newSlidingTilesScore3));
    }

    /**
     * Test whether method compareTo works with the names of the score holders.
     */
    @Test
    public void testCompareToNames() {
        User newUser1 = new User("1", "John");
        User newUser2 = new User("1", "Jane");
        ScoreSlidingTiles newSlidingTilesScore1 = new ScoreSlidingTiles(15, newUser1);
        ScoreSlidingTiles newSlidingTilesScore2 = new ScoreSlidingTiles(15, newUser1);
        ScoreSlidingTiles newSlidingTilesScore3 = new ScoreSlidingTiles(15, newUser2);

        assertEquals(-1, newSlidingTilesScore1.compareTo(newSlidingTilesScore3));
        assertEquals(0, newSlidingTilesScore1.compareTo(newSlidingTilesScore2));
        assertEquals(1, newSlidingTilesScore3.compareTo(newSlidingTilesScore1));
    }

    /**
     * Test whether method equals works.
     */
    @Test
    public void testEquals() {
        User newUser1 = new User("1", "John");
        User newUser2 = new User("1", "Jane");
        ScoreSlidingTiles newSlidingTilesScore1 = new ScoreSlidingTiles(15, newUser1);
        ScoreSlidingTiles newSlidingTilesScore2 = new ScoreSlidingTiles(15, newUser1);
        ScoreSlidingTiles newSlidingTilesScore3 = new ScoreSlidingTiles(15, newUser2);
        ScoreSlidingTiles newSlidingTilesScore4 = new ScoreSlidingTiles(10, newUser2);
        ScoreTicTacToe newTicTacToeScore1 = new ScoreTicTacToe(15, newUser2);

        assertEquals(true, newSlidingTilesScore1.equals(newSlidingTilesScore2));
        assertEquals(false, newSlidingTilesScore1.equals(null));
        assertEquals(false, newSlidingTilesScore3.equals(newTicTacToeScore1));
        assertEquals(false, newSlidingTilesScore3.equals(newSlidingTilesScore4));
    }
}
