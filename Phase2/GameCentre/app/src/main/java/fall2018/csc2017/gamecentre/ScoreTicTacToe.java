package fall2018.csc2017.gamecentre;

import android.support.annotation.NonNull;

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
        if(comparedToScore.getUserScore() < this.getUserScore()) {
            return 1;
        } else if (comparedToScore.getUserScore() > this.getUserScore()) {
            return -1;
        } else {
            return 0;
        }
    }
}
