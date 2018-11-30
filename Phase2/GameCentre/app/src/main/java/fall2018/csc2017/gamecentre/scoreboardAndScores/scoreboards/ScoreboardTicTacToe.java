package fall2018.csc2017.gamecentre.scoreboardAndScores.scoreboards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fall2018.csc2017.gamecentre.scoreboardAndScores.scores.ScoreTicTacToe;
import fall2018.csc2017.gamecentre.abstractClasses.Scoreboard;

/**
 * A scoreboard of tictactoe scores which can store and manipulate them.
 */
public class ScoreboardTicTacToe extends Scoreboard {

    /**
     * Score data is arranged in an array list. Smaller scores are closer to the top of the
     * scoreboard (similar (*but not same) concept to golf, where the less moves the better)
     */
    private ArrayList<ScoreTicTacToe> scoreBoardArray;

    /**
     * Creates a scoreboard
     *
     * @param listOfScores the list of scores
     * @param nameOfGame the name of the game that has these scores
     */
    public ScoreboardTicTacToe(List<ScoreTicTacToe> listOfScores, String nameOfGame) {
        super(nameOfGame);
        this.scoreBoardArray = (ArrayList<ScoreTicTacToe>) listOfScores;
    }

    /**
     * Returns the data within the array list of the scoreboard
     *
     * @return the score board data
     */
    public List<ScoreTicTacToe> getScoreBoardData() {
        return this.scoreBoardArray;
    }

    /**
     * Organizes and sorts the score board.
     */
    public void organizeScoreBoard() {
        Collections.sort(scoreBoardArray);
    }
}
