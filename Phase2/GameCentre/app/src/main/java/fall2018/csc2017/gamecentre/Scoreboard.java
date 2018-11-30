package fall2018.csc2017.gamecentre;

import java.util.ArrayList;
import java.util.List;

/**
 * The Scoreboard class
 */
abstract public class Scoreboard {

    /**
     * The name of the game that the scoreboard recorded scores for.
     */
    private String scoreboardGameName;

    /**
     * Creates a scoreboard
     *
     * @param nameOfGame the name of the game that has these scores
     */
    public Scoreboard(String nameOfGame) {
        this.scoreboardGameName = nameOfGame;
    }

    /**
     * Returns the name of the game for the scores in scoreboard
     *
     * @return the name of the game of the scores in the scoreboard
     */
    public String getScoreboardGameName() {
        return scoreboardGameName;
    }

    /**
     * Returns the data within the array list of the scoreboard
     *
     * @return the score board data
     */
    abstract public List<? extends ScoreAbstract> getScoreBoardData();

    /**
     * Organizes and sorts the score board.
     */
    abstract public void organizeScoreBoard();

    /**
     * Returns a list of strings where each string contains the rank, username and score of a user's
     * finished game
     *
     * @return a list of Strings form of the scoreboard data.
     */
    public List<String> getScoreBoardDataStringForm() {
        ArrayList<String> scoreBoardArrayStringForm = new ArrayList<>(0);
        scoreBoardArrayStringForm.add(scoreboardGameName + ":");
        int gameScoreRank = 1;
        for (ScoreAbstract score: this.getScoreBoardData()) {
            scoreBoardArrayStringForm.add(Integer.toString(gameScoreRank) +
                    ". " + score.toString());
            gameScoreRank = gameScoreRank + 1;
        }
        return scoreBoardArrayStringForm;
    }
}
