package fall2018.csc2017.gamecentre.scoreboard.scoreboardTests;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import fall2018.csc2017.gamecentre.ScoreAbstract;
import fall2018.csc2017.gamecentre.ScoreGo;
import fall2018.csc2017.gamecentre.ScoreboardGo;
import fall2018.csc2017.gamecentre.User;

import static org.junit.Assert.assertEquals;

public class scoreboardGoTest extends scoreboardTest {

    /**
     * Setup scores in preparation for tests.
     */
    protected List<ScoreGo> setupListOfScores() {
        List<ScoreGo> listOfScores = new ArrayList<>();
        User newUser = new User(1, "John", "Password");
        for (int i=1; i<6; i++) {
            ScoreGo newGoScore = new ScoreGo(i, newUser);
            listOfScores.add(newGoScore);
        }
        return listOfScores;
    }

    /**
     * Setup scoreboard in preparation for tests.
     */
    protected ScoreboardGo setupScoreboard(List<? extends ScoreAbstract> listOfScores) {
        return new ScoreboardGo(setupListOfScores(), "New Game");
    }

    /**
     * Test whether ScoreboardGo initialization works.
     */
    @Test
    public void testScoreboardInitialization() {
        ScoreboardGo newScoreboard = setupScoreboard(setupListOfScores());

        assertEquals("New Game", newScoreboard.getScoreboardGameName());
        assertEquals(setupListOfScores(), newScoreboard.getScoreBoardData());
    }

    /**
     * Test whether method getScoreBoardData works.
     */
    @Test
    public void testGetScoreBoardData() {
        ScoreboardGo newScoreboard = setupScoreboard(setupListOfScores());
        List<ScoreGo> listOfScores = setupListOfScores();

        assertEquals(listOfScores, newScoreboard.getScoreBoardData());
    }

    /**
     * Test whether method organizeScoreBoard works.
     */
    @Test
    public void testOrganizeScoreBoard() {
        List<ScoreGo> listOfScores = new ArrayList<>();
        User newUser = new User(1, "John", "Password");
        for (int i=5; i>0; i--) {
            ScoreGo newGoScore = new ScoreGo(i, newUser);
            listOfScores.add(newGoScore);
        }
        ScoreboardGo newScoreboard = new ScoreboardGo(listOfScores, "New Game");
        newScoreboard.organizeScoreBoard();

        List<ScoreGo> correctListOfScores = setupListOfScores();

        assertEquals(correctListOfScores, newScoreboard.getScoreBoardData());
    }
}
