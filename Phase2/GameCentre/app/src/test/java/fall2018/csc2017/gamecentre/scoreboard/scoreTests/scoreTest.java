package fall2018.csc2017.gamecentre.scoreboard.scoreTests;

import org.junit.Test;

import fall2018.csc2017.gamecentre.abstractClasses.ScoreAbstract;

import static org.junit.Assert.assertEquals;

/**
 * Set of tests for all scores.
 */
abstract public class scoreTest {

    /**
     * Setup scores in preparation for tests.
     */
    abstract protected ScoreAbstract setupScores();

    /**
     * Test whether the initialization of scores work.
     */
    @Test
    abstract public void testScoreInitialization();

    /**
     * Test whether method get_id works.
     */
    @Test
    public void testGet_id() {
        ScoreAbstract newScore = setupScores();

        assertEquals(1, newScore.getUserScore());
    }

//    /**
//     * Test whether method set_id works.
//     */
//    @Test
//    public void testSet_id() {
//        ScoreAbstract newScore = setupScores();
//        newScore.set_id(2);
//
//        assertEquals(2, newScore.getUserScore());
//    }

    /**
     * Test whether method getUserName works.
     */
    @Test
    public void testGetUserName() {
        ScoreAbstract newScore = setupScores();

        assertEquals("John", newScore.getOwner());
    }

    /**
     * Test whether method getUserScore works.
     */
    @Test
    public void testGetUserScore() {
        ScoreAbstract newScore = setupScores();

        assertEquals(10, newScore.getUserScore());
    }

    /**
     * Test whether method getUserScore works.
     */
    @Test
    public void testToString() {
        ScoreAbstract newScore = setupScores();

        assertEquals("John: 10", newScore.toString());
    }
}
