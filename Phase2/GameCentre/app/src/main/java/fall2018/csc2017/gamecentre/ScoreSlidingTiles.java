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
        if(comparedToScore.getUserScore() < this.getUserScore()) {
            return 1;
        } else if (comparedToScore.getUserScore() > this.getUserScore()) {
            return -1;
        } else {
            return Integer.compare(comparedToScore.getUserName().compareTo(this.getUserName()), 0);
        }
    }

    @Override
    public boolean equals(Object comparedToObject) {
        if (comparedToObject == null) {
            return false;
        } else if (!(comparedToObject instanceof ScoreSlidingTiles)) {
            return false;
        } else {
            ScoreSlidingTiles comparedToScore = (ScoreSlidingTiles) comparedToObject;
            return (this.getUserName().equals(comparedToScore.getUserName()) &&
                    (this.getUserScore() == comparedToScore.getUserScore()));
        }
    }
}
