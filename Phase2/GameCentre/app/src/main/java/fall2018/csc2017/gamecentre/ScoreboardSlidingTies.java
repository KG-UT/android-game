package fall2018.csc2017.gamecentre;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


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
     * Organizes and sorts the score board.
     */
    public void organizeScoreBoard() {
        Collections.sort(scoreBoardArray);
    }

    /**
     * Returns the data within the array list of the scoreboard
     *
     * @return the score board data
     */
    public List<ScoreSlidingTiles> getScoreBoardData() {
        return this.scoreBoardArray;
    }
}
