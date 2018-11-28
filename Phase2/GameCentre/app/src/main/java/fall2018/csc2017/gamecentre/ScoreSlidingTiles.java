package fall2018.csc2017.gamecentre;

import android.support.annotation.NonNull;

public class ScoreSlidingTiles extends ScoreAbstract implements Comparable<ScoreSlidingTiles> {

    /**
     * Creates a ScoreSlidingTiles where username comes from user (Used to create score at the endgame)
     */
    public ScoreSlidingTiles(int userScore, User user) {
        super(userScore, user);
    }

    /**
     * Creates a ScoreSlidingTiles where username comes from a string
     */
    public ScoreSlidingTiles(long _id, int userScore, String userName) {
        super(_id, userScore, userName);
    }

    @Override
    public int compareTo(@NonNull ScoreSlidingTiles comparedToScore) {
        if(comparedToScore.getUserScore() > this.getUserScore()) {
            return 1;
        } else if (comparedToScore.getUserScore() < this.getUserScore()) {
            return -1;
        } else {
            return 0;
        }
    }
}
