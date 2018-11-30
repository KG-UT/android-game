package fall2018.csc2017.gamecentre.scoreboard.scoreTests;

import org.junit.Test;

import fall2018.csc2017.gamecentre.ScoreSlidingTiles;
import fall2018.csc2017.gamecentre.ScoreTicTacToe;
import fall2018.csc2017.gamecentre.User;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class scoreTicTacToeTest extends scoreTest {

    /**
     * Setup scores in preparation for tests.
     */
    protected ScoreTicTacToe setupScores() {
        User newUser = new User("1", "John");
        return new ScoreTicTacToe(10, newUser);
    }

    /**
     * Test whether ScoreTicTacToe initialization works.
     */
    @Test
    public void testScoreInitialization() {
        User newUser = new User("1", "John");
        ScoreTicTacToe newTicTacToeScore1 = new ScoreTicTacToe(1, 10, newUser.getUsername());
        ScoreTicTacToe newTicTacToeScore2 = new ScoreTicTacToe(10, newUser);

        assertEquals(1, newTicTacToeScore1.get_id());
        assertEquals(10, newTicTacToeScore1.getUserScore());
        assertEquals(newUser.getUsername(), newTicTacToeScore1.getUserName());

        assertEquals(1, newTicTacToeScore2.get_id());
        assertEquals(10, newTicTacToeScore2.getUserScore());
        assertEquals(newUser.getUsername(), newTicTacToeScore2.getUserName());
    }

    /**
     * Test whether method compareTo works with score numerical values.
     */
    @Test
    public void testCompareToNumbers() {
        User newUser1 = new User("1", "John");
        ScoreTicTacToe newTicTacToeScore1 = new ScoreTicTacToe(20, newUser1);
        ScoreTicTacToe newTicTacToeScore2 = new ScoreTicTacToe(15, newUser1);
        ScoreTicTacToe newTicTacToeScore3 = new ScoreTicTacToe(15, newUser1);

        assertEquals(-1, newTicTacToeScore1.compareTo(newTicTacToeScore2));
        assertEquals(1, newTicTacToeScore2.compareTo(newTicTacToeScore1));

        assertNotEquals(-1, newTicTacToeScore2.compareTo(newTicTacToeScore3));
        assertNotEquals(1, newTicTacToeScore2.compareTo(newTicTacToeScore3));
    }

    /**
     * Test whether method compareTo works with the names of the score holders.
     */
    @Test
    public void testCompareToNames() {
        User newUser1 = new User("1", "John");
        User newUser2 = new User("1", "Jane");
        ScoreTicTacToe newTicTacToeScore1 = new ScoreTicTacToe(15, newUser1);
        ScoreTicTacToe newTicTacToeScore2 = new ScoreTicTacToe(15, newUser1);
        ScoreTicTacToe newTicTacToeScore3 = new ScoreTicTacToe(15, newUser2);

        assertEquals(-1, newTicTacToeScore1.compareTo(newTicTacToeScore3));
        assertEquals(0, newTicTacToeScore1.compareTo(newTicTacToeScore2));
        assertEquals(1, newTicTacToeScore3.compareTo(newTicTacToeScore1));
    }

    /**
     * Test whether method equals works.
     */
    @Test
    public void testEquals() {
        User newUser1 = new User("1", "John");
        User newUser2 = new User("1", "Jane");
        ScoreTicTacToe newTicTacToeScore1 = new ScoreTicTacToe(15, newUser1);
        ScoreTicTacToe newTicTacToeScore2 = new ScoreTicTacToe(15, newUser1);
        ScoreTicTacToe newTicTacToeScore3 = new ScoreTicTacToe(15, newUser2);
        ScoreTicTacToe newTicTacToeScore4 = new ScoreTicTacToe(10, newUser2);
        ScoreSlidingTiles newSlidingTilesScore1 = new ScoreSlidingTiles(15, newUser2);

        assertEquals(true, newTicTacToeScore1.equals(newTicTacToeScore2));
        assertEquals(false, newTicTacToeScore1.equals(null));
        assertEquals(false, newTicTacToeScore3.equals(newSlidingTilesScore1));
        assertEquals(false, newTicTacToeScore3.equals(newTicTacToeScore4));
    }
}
