package fall2018.csc2017.gamecentre.scoreboard.scoreboardTests;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import fall2018.csc2017.gamecentre.abstractClasses.ScoreAbstract;
import fall2018.csc2017.gamecentre.abstractClasses.Scoreboard;

import static org.junit.Assert.assertEquals;

abstract public class scoreboardTest {

    /**
     * Setup scores in preparation for tests.
     */
    abstract protected List<? extends ScoreAbstract> setupListOfScores();

    /**
     * Setup scoreboard in preparation for tests.
     */
    abstract protected Scoreboard setupScoreboard(List<? extends ScoreAbstract> listOfScores);

    /**
     * Test whether the initialization of scoreboards work.
     */
    @Test
    abstract public void testScoreboardInitialization();

    /**
     * Test whether method getScoreboardGameName works.
     */
    @Test
    public void testGetScoreboardGameName() {
        Scoreboard newScoreboard = setupScoreboard(setupListOfScores());

        assertEquals("New Game", newScoreboard.getScoreboardGameName());
    }

    /**
     * Test whether method getScoreBoardData works.
     */
    @Test
    public void testGetScoreBoardDataStringForm() {
        Scoreboard newScoreboard = setupScoreboard(setupListOfScores());
        ArrayList<String> correctScoreboardString = new ArrayList<>();
        correctScoreboardString.add("New Game:");
        correctScoreboardString.add("1. John: 1");
        correctScoreboardString.add("2. John: 2");
        correctScoreboardString.add("3. John: 3");
        correctScoreboardString.add("4. John: 4");
        correctScoreboardString.add("5. John: 5");

        assertEquals(correctScoreboardString, newScoreboard.getScoreBoardDataStringForm());

    }

}
