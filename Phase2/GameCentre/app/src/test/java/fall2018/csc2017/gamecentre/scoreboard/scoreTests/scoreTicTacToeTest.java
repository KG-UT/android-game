package fall2018.csc2017.gamecentre.scoreboard.scoreTests;

import org.junit.Test;

import fall2018.csc2017.gamecentre.scoreboardAndScores.scores.ScoreSlidingTiles;
import fall2018.csc2017.gamecentre.scoreboardAndScores.scores.ScoreTicTacToe;
import fall2018.csc2017.gamecentre.User;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Set of tests for ScoreTicTacToe.
 */
public class scoreTicTacToeTest extends scoreTest {

    /**
     * Setup scores in preparation for tests.
     */
    protected ScoreTicTacToe setupScores() {
        User newUser = new User("1", "John");
        return new ScoreTicTacToe(10, newUser.getUsername());
    }

    /**
     * Test whether ScoreTicTacToe initialization works.
     */
    @Test
    public void testScoreInitialization() {
        User newUser = new User("1", "John");
        ScoreTicTacToe newScore1 = new ScoreTicTacToe(1,  newUser.getUsername());
        ScoreTicTacToe newScore2 = new ScoreTicTacToe(10, newUser.getUsername());

        assertEquals(1, newScore1.getUserScore());
        assertEquals(newUser.getUsername(), newScore1.getOwner());

        assertEquals(10, newScore2.getUserScore());
        assertEquals(newUser.getUsername(), newScore2.getOwner());
    }

    /**
     * Test whether method compareTo works with score numerical values.
     */
    @Test
    public void testCompareToNumbers() {
        User newUser1 = new User("1", "John");
        ScoreTicTacToe newScore1 = new ScoreTicTacToe(20, newUser1.getUsername());
        ScoreTicTacToe newScore2 = new ScoreTicTacToe(15, newUser1.getUsername());
        ScoreTicTacToe newScore3 = new ScoreTicTacToe(15, newUser1.getUsername());

        assertEquals(-1, newScore1.compareTo(newScore2));
        assertEquals(1, newScore2.compareTo(newScore1));

        assertNotEquals(-1, newScore2.compareTo(newScore3));
        assertNotEquals(1, newScore2.compareTo(newScore3));
    }

    /**
     * Test whether method compareTo works with the names of the score holders.
     */
    @Test
    public void testCompareToNames() {
        User newUser1 = new User("1", "John");
        User newUser2 = new User("1", "Jane");
        ScoreTicTacToe newScore1 = new ScoreTicTacToe(15, newUser1.getUsername());
        ScoreTicTacToe newScore2 = new ScoreTicTacToe(15, newUser1.getUsername());
        ScoreTicTacToe newScore3 = new ScoreTicTacToe(15, newUser2.getUsername());

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
        ScoreTicTacToe newScore1 = new ScoreTicTacToe(15, newUser1.getUsername());
        ScoreTicTacToe newScore2 = new ScoreTicTacToe(15, newUser1.getUsername());
        ScoreTicTacToe newScore3 = new ScoreTicTacToe(15, newUser2.getUsername());
        ScoreTicTacToe newScore4 = new ScoreTicTacToe(10, newUser2.getUsername());
        ScoreSlidingTiles newSlidingTilesScore = new ScoreSlidingTiles(15, newUser2.getUsername());

        assertEquals(true, newScore1.equals(newScore2));
        assertEquals(false, newScore1.equals(null));
        assertEquals(false, newScore3.equals(newSlidingTilesScore));
        assertEquals(false, newScore3.equals(newScore4));
    }
}
