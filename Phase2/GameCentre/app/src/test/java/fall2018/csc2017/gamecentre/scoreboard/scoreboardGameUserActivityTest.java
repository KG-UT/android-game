package fall2018.csc2017.gamecentre.scoreboard;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import fall2018.csc2017.gamecentre.ScoreMatchingCards;
import fall2018.csc2017.gamecentre.ScoreSlidingTiles;
import fall2018.csc2017.gamecentre.ScoreTicTacToe;
import fall2018.csc2017.gamecentre.ScoreboardMatchingCards;
import fall2018.csc2017.gamecentre.ScoreboardSlidingTies;
import fall2018.csc2017.gamecentre.ScoreboardTicTacToe;
import fall2018.csc2017.gamecentre.User;

import static org.junit.Assert.assertEquals;

public class scoreboardGameUserActivityTest {

    /**
     * Test whether method setupSlidingTilesScoreboard works.
     */
    @Test
    public void testSetupSlidingTilesScoreboard() {
        List<ScoreSlidingTiles> listOfScores = new ArrayList<>();
        List<ScoreSlidingTiles> correctListOfScores = new ArrayList<>();
        User newUser = new User(1, "John", "Password");
        for (int i=1; i<6; i++) {
            ScoreSlidingTiles newTicTacToeScore = new ScoreSlidingTiles(6-i, newUser);
            listOfScores.add(newTicTacToeScore);

            ScoreSlidingTiles newCorrectSlidingTilesScore = new ScoreSlidingTiles(i, newUser);
            correctListOfScores.add(newCorrectSlidingTilesScore);
        }
        ScoreboardSlidingTies newScoreboard = new ScoreboardSlidingTies(listOfScores, "New Game");
        newScoreboard.organizeScoreBoard();

        assertEquals("New Game", newScoreboard.getScoreboardGameName());
        assertEquals(correctListOfScores, newScoreboard.getScoreBoardData());
    }

    /**
     * Test whether method setupGoScoreboard works.
     */
    @Test
    public void testSetupGoScoreboard() {
        List<ScoreMatchingCards> listOfScores = new ArrayList<>();
        List<ScoreMatchingCards> correctListOfScores = new ArrayList<>();
        User newUser = new User(1, "John", "Password");
        for (int i=1; i<6; i++) {
            ScoreMatchingCards newGoScore = new ScoreMatchingCards(6-i, newUser);
            listOfScores.add(newGoScore);

            ScoreMatchingCards newCorrectSlidingTilesScore = new ScoreMatchingCards(i, newUser);
            correctListOfScores.add(newCorrectSlidingTilesScore);
        }
        ScoreboardMatchingCards newScoreboard = new ScoreboardMatchingCards(listOfScores, "New Game");
        newScoreboard.organizeScoreBoard();

        assertEquals("New Game", newScoreboard.getScoreboardGameName());
        assertEquals(correctListOfScores, newScoreboard.getScoreBoardData());
    }

    /**
     * Test whether method setupTicTacToeScoreboard works.
     */
    @Test
    public void testSetupTicTacToeScoreboard() {
        List<ScoreTicTacToe> listOfScores = new ArrayList<>();
        List<ScoreTicTacToe> correctListOfScores = new ArrayList<>();
        User newUser = new User(1, "John", "Password");
        for (int i=1; i<6; i++) {
            ScoreTicTacToe newTicTacToeScore = new ScoreTicTacToe(i, newUser);
            listOfScores.add(newTicTacToeScore);

            ScoreTicTacToe newCorrectTicTacToeScore = new ScoreTicTacToe(6-i, newUser);
            correctListOfScores.add(newCorrectTicTacToeScore);
        }
        ScoreboardTicTacToe newScoreboard = new ScoreboardTicTacToe(listOfScores, "New Game");
        newScoreboard.organizeScoreBoard();

        assertEquals("New Game", newScoreboard.getScoreboardGameName());
        assertEquals(correctListOfScores, newScoreboard.getScoreBoardData());
    }
}
