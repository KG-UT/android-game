package fall2018.csc2017.gamecentre;

import org.junit.Test;
import static org.junit.Assert.*;

public class ScoreTest {
    /**
     * Test constructors
     */
    @Test
    public void testIntialization() {
        User testUser = new User(123, "testUser", "test");
        Score score = new Score(123, 10, "testUser");
        assertEquals(10, score.getUserScore());
        assertEquals(123, score.get_id());
        assertEquals("testUser", score.getUserName());

        score = new Score(123, 10, "testUser");
        assertEquals(10, score.getUserScore());
        assertEquals(123, score.get_id());
        assertEquals("testUser", score.getUserName());
    }

    /**
     * Test id setter
     */
    @Test
    public void testSetId() {
        Score score = new Score(123, 10, "testUser");
        score.set_id(123123);
        assertEquals(score.get_id(), 123123);
    }


    /**
     * Test toString
     */
    @Test
    public void testToString() {
        Score score = new Score(123, 10, "testUser");
        String scoreStr = score.toString();
        assertEquals("testUser: 10", scoreStr);
    }

    /**
     * Test compareTo
     */
    @Test
    public void testCompareTo() {
        Score score1 = new Score(123, 10, "testUser");
        Score score2 = new Score(123, 12, "testUser");
        assertEquals(true, score1.compareTo(score2) > 0);
        assertEquals(false, score2.compareTo(score1) > 0);
        assertEquals(0, score1.compareTo(score1));
    }

}
