package fall2018.csc2017.gamecentre;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The Scoreboard class
 */
public class Scoreboard {

    /**
     * Score data is arranged in an array list. Smaller scores are closer to the top of the
     * scoreboard (similar (*but not same) concept to golf, where the less moves the better)
     */
    private ArrayList<Score> scoreBoardArray;

    /**
     * Creates a scoreboard
     *
     * @param listOfScores the list of scores
     */
    public Scoreboard(List<Score> listOfScores) {
        this.scoreBoardArray = (ArrayList<Score>) listOfScores;
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
    public List<Score> getScoreBoardData() {
        return this.scoreBoardArray;
    }

    /**
     * Returns a list of strings where each string contains the rank, username and score of a user's
     * finished game
     *
     * @return a list of Strings form of the scoreboard data.
     */
    public List<String> getScoreBoardDataStringForm() {
        ArrayList<String> scoreBoardArrayStringForm = new ArrayList<>(0);
        int gameScoreRank = 1;
        for (Score score: this.getScoreBoardData()) {
            scoreBoardArrayStringForm.add(Integer.toString(gameScoreRank) +
                    ". " + score.toString());
            gameScoreRank = gameScoreRank + 1;
        }
        return scoreBoardArrayStringForm;
    }
}
