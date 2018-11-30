package fall2018.csc2017.gamecentre.scoreboardAndScores.scoreboards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fall2018.csc2017.gamecentre.scoreboardAndScores.scores.ScoreSlidingTiles;
import fall2018.csc2017.gamecentre.abstractClasses.Scoreboard;

/**
 * A scoreboard of sliding tile scores which can store and manipulate them.
 */
public class ScoreboardSlidingTies extends Scoreboard {

    /**
     * Score data is arranged in an array list. Smaller scores are closer to the top of the
     * scoreboard (similar (*but not same) concept to golf, where the less moves the better)
     */
    private ArrayList<ScoreSlidingTiles> scoreBoardArray;

    /**
     * Creates a scoreboard
     *
     * @param listOfScores the list of scores
     * @param nameOfGame the name of the game that has these scores
     */
    public ScoreboardSlidingTies(List<ScoreSlidingTiles> listOfScores, String nameOfGame) {
        super(nameOfGame);
        this.scoreBoardArray = (ArrayList<ScoreSlidingTiles>) listOfScores;
    }

    /**
     * Returns the data within the array list of the scoreboard
     *
     * @return the score board data
     */
    public List<ScoreSlidingTiles> getScoreBoardData() {
        return this.scoreBoardArray;
    }

    /**
     * Organizes and sorts the score board.
     */
    public void organizeScoreBoard() {
        Collections.sort(scoreBoardArray);
    }
}
