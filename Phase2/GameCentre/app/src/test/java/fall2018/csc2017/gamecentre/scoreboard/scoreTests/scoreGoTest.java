package fall2018.csc2017.gamecentre.scoreboard.scoreTests;

import org.junit.Test;

import fall2018.csc2017.gamecentre.ScoreMatchingCards;
import fall2018.csc2017.gamecentre.ScoreTicTacToe;
import fall2018.csc2017.gamecentre.User;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class scoreGoTest extends scoreTest {

    /**
     * Setup scores in preparation for tests.
     */
    protected ScoreMatchingCards setupScores() {
        User newUser = new User(1, "John", "Password");
        return new ScoreMatchingCards(10, newUser);
    }

    /**
     * Test whether ScoreMatchingCards initialization works.
     */
    @Test
    public void testScoreInitialization() {
        User newUser = new User(1, "John", "Password");
        ScoreMatchingCards newGoScore1 = new ScoreMatchingCards(1, 10, newUser.getUsername());
        ScoreMatchingCards newGoScore2 = new ScoreMatchingCards(10, newUser);

        assertEquals(1, newGoScore1.get_id());
        assertEquals(10, newGoScore1.getUserScore());
        assertEquals(newUser.getUsername(), newGoScore1.getUserName());

        assertEquals(1, newGoScore2.get_id());
        assertEquals(10, newGoScore2.getUserScore());
        assertEquals(newUser.getUsername(), newGoScore2.getUserName());
    }

    /**
     * Test whether method compareTo works with score numerical values.
     */
    @Test
    public void testCompareToNumbers() {
        User newUser1 = new User(1, "John", "Password");
        ScoreMatchingCards newGoScore1 = new ScoreMatchingCards(20, newUser1);
        ScoreMatchingCards newGoScore2 = new ScoreMatchingCards(15, newUser1);
        ScoreMatchingCards newGoScore3 = new ScoreMatchingCards(15, newUser1);

        assertEquals(1, newGoScore1.compareTo(newGoScore2));
        assertEquals(-1, newGoScore2.compareTo(newGoScore1));

        assertNotEquals(1, newGoScore2.compareTo(newGoScore3));
        assertNotEquals(-1, newGoScore2.compareTo(newGoScore3));
    }

    /**
     * Test whether method compareTo works with the names of the score holders.
     */
    @Test
    public void testCompareToNames() {
        User newUser1 = new User(1, "John", "Password");
        User newUser2 = new User(1, "Jane", "Password");
        ScoreMatchingCards newGoScore1 = new ScoreMatchingCards(15, newUser1);
        ScoreMatchingCards newGoScore2 = new ScoreMatchingCards(15, newUser1);
        ScoreMatchingCards newGoScore3 = new ScoreMatchingCards(15, newUser2);

        assertEquals(-1, newGoScore1.compareTo(newGoScore3));
        assertEquals(0, newGoScore1.compareTo(newGoScore2));
        assertEquals(1, newGoScore3.compareTo(newGoScore1));
    }

    /**
     * Test whether method equals works.
     */
    @Test
    public void testEquals() {
        User newUser1 = new User(1, "John", "Password");
        User newUser2 = new User(1, "Jane", "Password");
        ScoreMatchingCards newGoScore1 = new ScoreMatchingCards(15, newUser1);
        ScoreMatchingCards newGoScore2 = new ScoreMatchingCards(15, newUser1);
        ScoreMatchingCards newGoScore3 = new ScoreMatchingCards(15, newUser2);
        ScoreMatchingCards newGoScore4 = new ScoreMatchingCards(10, newUser2);
        ScoreTicTacToe newTicTacToeScore1 = new ScoreTicTacToe(15, newUser2);

        assertEquals(true, newGoScore1.equals(newGoScore2));
        assertEquals(false, newGoScore1.equals(null));
        assertEquals(false, newGoScore3.equals(newTicTacToeScore1));
        assertEquals(false, newGoScore3.equals(newGoScore4));
    }
}
