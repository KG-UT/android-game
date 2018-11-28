package fall2018.csc2017.gamecentre.scoreboard;

import org.junit.Test;

import fall2018.csc2017.gamecentre.ScoreGo;
import fall2018.csc2017.gamecentre.User;

import static org.junit.Assert.assertEquals;

public class scoreGoTest extends scoreTest{

    /**
     * Test whether method get_id works.
     */
    @Test
    public void testGet_id() {
        User newUser = new User(1, "Username", "Password");
        ScoreGo newGoScore = new ScoreGo(10, newUser);

        assertEquals(1, newGoScore.get_id());
    }

    /**
     * Test whether method set_id works.
     */
    @Test
    public void testSet_id() {
        User newUser = new User(1, "Username", "Password");
        ScoreGo newGoScore = new ScoreGo(10, newUser);
        newGoScore.set_id(2);

        assertEquals(2, newGoScore.get_id());
    }

    /**
     * Test whether method getUserName works.
     */
    @Test
    public void testGetUserName() {
        User newUser = new User(1, "Username", "Password");
        ScoreGo newGoScore = new ScoreGo(10, newUser);

        assertEquals("Username", newGoScore.getUserName());
    }

    /**
     * Test whether method getUserScore works.
     */
    @Test
    public void testGetUserScore() {
        User newUser = new User(1, "Username", "Password");
        ScoreGo newGoScore = new ScoreGo(10, newUser);

        assertEquals(10, newGoScore.getUserScore());
    }

    /**
     * Test whether method getUserScore works.
     */
    @Test
    public void testToString() {
        User newUser = new User(1, "Username", "Password");
        ScoreGo newGoScore = new ScoreGo(10, newUser);

        assertEquals("Username: 10", newGoScore.toString());
    }

    /**
     * Test whether method compareTo works when the current value is larger than the to be compared
     * value.
     */
    @Test
    public void testCompareToLargerThan() {
        User newUser = new User(1, "Username", "Password");
        ScoreGo newGoScore1 = new ScoreGo(15, newUser);
        ScoreGo newGoScore2 = new ScoreGo(10, newUser);

        assertEquals(1, newGoScore1.compareTo(newGoScore2));
    }

    /**
     * Test whether method compareTo works when the current value is equal to the to be compared
     * value.
     */
    @Test
    public void testCompareToEqual() {
        User newUser = new User(1, "Username", "Password");
        ScoreGo newGoScore1 = new ScoreGo(10, newUser);
        ScoreGo newGoScore2 = new ScoreGo(10, newUser);

        assertEquals(0, newGoScore1.compareTo(newGoScore2));
    }

    /**
     * Test whether method compareTo works when the current value is less than the to be compared
     * value.
     */
    @Test
    public void testCompareToLesserThan() {
        User newUser = new User(1, "Username", "Password");
        ScoreGo newGoScore1 = new ScoreGo(10, newUser);
        ScoreGo newGoScore2 = new ScoreGo(15, newUser);

        assertEquals(-1, newGoScore1.compareTo(newGoScore2));
    }
}
