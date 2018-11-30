package fall2018.csc2017.gamecentre.scoreboardAndScores.scores;

import android.support.annotation.NonNull;

import fall2018.csc2017.gamecentre.User;
import fall2018.csc2017.gamecentre.abstractClasses.ScoreAbstract;

public class ScoreTicTacToe extends ScoreAbstract implements Comparable<ScoreTicTacToe> {

    /**
     * Creates a ScoreTicTacToe where username comes from user (Used to create score at the endgame)
     */
    public ScoreTicTacToe(int userScore, User user) {
        super(userScore, user);
    }

    /**
     * Creates a ScoreTicTacToe where username comes from a string
     */
    public ScoreTicTacToe(long _id, int userScore, String userName) {
        super(_id, userScore, userName);
    }

    @Override
    public int compareTo(@NonNull ScoreTicTacToe comparedToScore) {
        if(comparedToScore.getUserScore() > this.getUserScore()) {
            return 1;
        } else if (comparedToScore.getUserScore() < this.getUserScore()) {
            return -1;
        } else {
            return Integer.compare(comparedToScore.getUserName().compareTo(this.getUserName()), 0);
        }
    }

    @Override
    public boolean equals(Object comparedToObject) {
        if (comparedToObject == null) {
            return false;
        } else if (!(comparedToObject instanceof ScoreTicTacToe)) {
            return false;
        } else {
            ScoreTicTacToe comparedToScore = (ScoreTicTacToe) comparedToObject;
            return (this.getUserName().equals(comparedToScore.getUserName()) &&
                    (this.getUserScore() == comparedToScore.getUserScore()));
        }
    }
}
