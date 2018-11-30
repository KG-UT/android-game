package fall2018.csc2017.gamecentre.scoreboard.scoreboardTests;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import fall2018.csc2017.gamecentre.abstractClasses.ScoreAbstract;

import fall2018.csc2017.gamecentre.scoreboardAndScores.scores.ScoreMatchingCards;
import fall2018.csc2017.gamecentre.scoreboardAndScores.scoreboards.ScoreboardMatchingCards;
import fall2018.csc2017.gamecentre.User;

import static org.junit.Assert.assertEquals;

public class scoreboardGoTest extends scoreboardTest {

    /**
     * Setup scores in preparation for tests.
     */

    protected List<ScoreMatchingCards> setupListOfScores() {
        List<ScoreMatchingCards> listOfScores = new ArrayList<>();
        User newUser = new User("1", "John");
        for (int i=1; i<6; i++) {
            ScoreMatchingCards newGoScore = new ScoreMatchingCards(i, newUser);
            listOfScores.add(newGoScore);
        }
        return listOfScores;
    }

    /**
     * Setup scoreboard in preparation for tests.
     */
    protected ScoreboardMatchingCards setupScoreboard(List<? extends ScoreAbstract> listOfScores) {
        return new ScoreboardMatchingCards(setupListOfScores(), "New Game");
    }

    /**
     * Test whether ScoreboardMatchingCards initialization works.
     */
    @Test
    public void testScoreboardInitialization() {
        ScoreboardMatchingCards newScoreboard = setupScoreboard(setupListOfScores());

        assertEquals("New Game", newScoreboard.getScoreboardGameName());
        assertEquals(setupListOfScores(), newScoreboard.getScoreBoardData());
    }

    /**
     * Test whether method getScoreBoardData works.
     */
    @Test
    public void testGetScoreBoardData() {
        ScoreboardMatchingCards newScoreboard = setupScoreboard(setupListOfScores());
        List<ScoreMatchingCards> listOfScores = setupListOfScores();

        assertEquals(listOfScores, newScoreboard.getScoreBoardData());
    }

    /**
     * Test whether method organizeScoreBoard works.
     */
    @Test
    public void testOrganizeScoreBoard() {

        List<ScoreMatchingCards> listOfScores = new ArrayList<>();
        User newUser = new User("1", "John");
        for (int i=5; i>0; i--) {
            ScoreMatchingCards newGoScore = new ScoreMatchingCards(i, newUser);
            listOfScores.add(newGoScore);
        }
        ScoreboardMatchingCards newScoreboard = new ScoreboardMatchingCards(listOfScores, "New Game");
        newScoreboard.organizeScoreBoard();

        List<ScoreMatchingCards> correctListOfScores = setupListOfScores();

        assertEquals(correctListOfScores, newScoreboard.getScoreBoardData());
    }
}
