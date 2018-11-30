package fall2018.csc2017.gamecentre.scoreboard.scoreboardTests;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import fall2018.csc2017.gamecentre.abstractClasses.ScoreAbstract;
import fall2018.csc2017.gamecentre.scoreboardAndScores.scores.ScoreSlidingTiles;
import fall2018.csc2017.gamecentre.scoreboardAndScores.scoreboards.ScoreboardSlidingTies;
import fall2018.csc2017.gamecentre.User;

import static org.junit.Assert.assertEquals;

/**
 * Set of tests for ScoreboardSlidingTiles.
 */
public class scoreboardSlidingTilesTest extends scoreboardTest {

    /**
     * Setup scores in preparation for tests.
     */
    protected List<ScoreSlidingTiles> setupListOfScores() {
        List<ScoreSlidingTiles> listOfScores = new ArrayList<>();
        User newUser = new User("1", "John");

        for (int i=1; i<6; i++) {
            ScoreSlidingTiles newSlidingTilesScore = new ScoreSlidingTiles(i, newUser.getUsername());
            listOfScores.add(newSlidingTilesScore);
        }

        return listOfScores;
    }

    /**
     * Setup scoreboard in preparation for tests.
     */
    protected ScoreboardSlidingTies setupScoreboard(List<? extends ScoreAbstract> listOfScores) {
        return new ScoreboardSlidingTies(setupListOfScores(), "New Game");
    }

    /**
     * Test whether ScoreboardSlidingTiles initialization works.
     */
    @Test
    public void testScoreboardInitialization() {
        ScoreboardSlidingTies newScoreboard = setupScoreboard(setupListOfScores());

        assertEquals("New Game", newScoreboard.getScoreboardGameName());
        assertEquals(setupListOfScores(), newScoreboard.getScoreBoardData());
    }

    /**
     * Test whether method getScoreBoardData works.
     */
    @Test
    public void testGetScoreBoardData() {
        ScoreboardSlidingTies newScoreboard = setupScoreboard(setupListOfScores());
        List<ScoreSlidingTiles> listOfScores = setupListOfScores();

        assertEquals(listOfScores, newScoreboard.getScoreBoardData());
    }

    /**
     * Test whether method organizeScoreBoard works.
     */
    @Test
    public void testOrganizeScoreBoard() {
        List<ScoreSlidingTiles> listOfScores = new ArrayList<>();
        User newUser = new User("1", "John");

        for (int i=5; i>0; i--) {
            ScoreSlidingTiles newSlidingTilesScore = new ScoreSlidingTiles(i, newUser.getUsername());
            listOfScores.add(newSlidingTilesScore);
        }
        ScoreboardSlidingTies newScoreboard = new ScoreboardSlidingTies(listOfScores, "New Game");
        newScoreboard.organizeScoreBoard();

        List<ScoreSlidingTiles> correctListOfScores = setupListOfScores();

        assertEquals(correctListOfScores, newScoreboard.getScoreBoardData());
    }
}
