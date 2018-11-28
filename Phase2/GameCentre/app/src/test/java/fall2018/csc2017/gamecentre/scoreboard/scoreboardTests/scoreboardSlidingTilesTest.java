package fall2018.csc2017.gamecentre.scoreboard.scoreboardTests;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import fall2018.csc2017.gamecentre.ScoreSlidingTiles;
import fall2018.csc2017.gamecentre.ScoreboardSlidingTies;
import fall2018.csc2017.gamecentre.User;

import static org.junit.Assert.assertEquals;

public class scoreboardSlidingTilesTest extends scoreboardTest {

    /**
     * Test whether method getScoreBoardData works.
     */
    @Test
    public void testGetScoreBoardData() {
        List<ScoreSlidingTiles> listOfScores = new ArrayList<>();
        User newUser = new User(1, "Username", "Password");
        for (int i=0; i<20; i++) {
            ScoreSlidingTiles newSlidingTilesScore = new ScoreSlidingTiles(i, newUser);
            listOfScores.add(newSlidingTilesScore);
        }
        ScoreboardSlidingTies newScoreboard = new ScoreboardSlidingTies(listOfScores, "Sliding Tiles");

        assertEquals(listOfScores, newScoreboard.getScoreBoardData());
    }

    /**
     * Test whether method organizeScoreBoard works.
     */
    @Test
    public void testOrganizeScoreBoard() {
        List<ScoreSlidingTiles> listOfScores = new ArrayList<>();
        User newUser = new User(1, "Username", "Password");
        for (int i=20; i>0; i--) {
            ScoreSlidingTiles newSlidingTilesScore = new ScoreSlidingTiles(i, newUser);
            listOfScores.add(newSlidingTilesScore);
        }
        ScoreboardSlidingTies newScoreboard = new ScoreboardSlidingTies(listOfScores, "Sliding Tiles");
        newScoreboard.organizeScoreBoard();

        List<ScoreSlidingTiles> correctListOfScores = new ArrayList<>();
        for (int i=1; i<21; i++) {
            ScoreSlidingTiles newSlidingTilesScore = new ScoreSlidingTiles(i, newUser);
            correctListOfScores.add(newSlidingTilesScore);
        }
        assertEquals(correctListOfScores, newScoreboard.getScoreBoardData());
    }
}
