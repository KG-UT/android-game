package fall2018.csc2017.gamecentre;

import android.support.annotation.NonNull;

public class ScoreGo extends ScoreAbstract implements Comparable<ScoreGo> {

    /**
     * Creates a ScoreGo where username comes from user (Used to create score at the endgame)
     */
    public ScoreGo(int userScore, User user) {
        super(userScore, user);
    }

    /**
     * Creates a ScoreGo where username comes from a string
     */
    public ScoreGo(long _id, int userScore, String userName) {
        super(_id, userScore, userName);
    }

    @Override
    public int compareTo(@NonNull ScoreGo comparedToScore) {
        if(comparedToScore.getUserScore() > this.getUserScore()) {
            return 1;
        } else if (comparedToScore.getUserScore() < this.getUserScore()) {
            return -1;
        } else {
            return 0;
        }
    }
}
