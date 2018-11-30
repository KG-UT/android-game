package fall2018.csc2017.gamecentre.scoreboard.scoreboardTests;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import fall2018.csc2017.gamecentre.ScoreAbstract;
import fall2018.csc2017.gamecentre.ScoreSlidingTiles;
import fall2018.csc2017.gamecentre.ScoreTicTacToe;
import fall2018.csc2017.gamecentre.ScoreboardSlidingTies;
import fall2018.csc2017.gamecentre.ScoreboardTicTacToe;
import fall2018.csc2017.gamecentre.User;

import static org.junit.Assert.assertEquals;

public class scoreboardTicTacToeTest extends scoreboardTest {

    /**
     * Setup scores in preparation for tests.
     */
    protected List<ScoreTicTacToe> setupListOfScores() {
        List<ScoreTicTacToe> listOfScores = new ArrayList<>();
        User newUser = new User("1", "John");
        for (int i=1; i<6; i++) {
            ScoreTicTacToe newTicTacToeScore = new ScoreTicTacToe(i, newUser);
            listOfScores.add(newTicTacToeScore);
        }
        return listOfScores;
    }

    /**
     * Setup scoreboard in preparation for tests.
     */
    protected ScoreboardTicTacToe setupScoreboard(List<? extends ScoreAbstract> listOfScores) {
        return new ScoreboardTicTacToe(setupListOfScores(), "New Game");
    }

    /**
     * Test whether ScoreboardTicTacToe initialization works.
     */
    @Test
    public void testScoreboardInitialization() {
        ScoreboardTicTacToe newScoreboard = setupScoreboard(setupListOfScores());

        assertEquals("New Game", newScoreboard.getScoreboardGameName());
        assertEquals(setupListOfScores(), newScoreboard.getScoreBoardData());
    }

    /**
     * Test whether method getScoreBoardData works.
     */
    @Test
    public void testGetScoreBoardData() {
        User newUser = new User("1", "Username");
        List<ScoreTicTacToe> listOfScores = setupListOfScores();

        for (int i=0; i<20; i++) {
            ScoreTicTacToe newSlidingTilesScore = new ScoreTicTacToe(i, newUser);
            listOfScores.add(newSlidingTilesScore);
        }
        ScoreboardTicTacToe newScoreboard = setupScoreboard(setupListOfScores());

        assertEquals(listOfScores, newScoreboard.getScoreBoardData());
    }

    /**
     * Test whether method organizeScoreBoard works.
     */
    @Test
    public void testOrganizeScoreBoard() {
        List<ScoreTicTacToe> listOfScores = new ArrayList<>();

        User newUser = new User("1", "Username");
        for (int i=20; i>0; i--) {
            ScoreTicTacToe newSlidingTilesScore = new ScoreTicTacToe(i, newUser);
            listOfScores.add(newSlidingTilesScore);
        }
        ScoreboardTicTacToe newScoreboard = new ScoreboardTicTacToe(listOfScores, "Sliding Tiles");

        newScoreboard.organizeScoreBoard();

        List<ScoreTicTacToe> correctListOfScores = new ArrayList<>();
        for (int i=5; i>0; i--) {
            ScoreTicTacToe newTicTacToeScore = new ScoreTicTacToe(i, newUser);
            correctListOfScores.add(newTicTacToeScore);
        }

        assertEquals(correctListOfScores, newScoreboard.getScoreBoardData());
    }
}
