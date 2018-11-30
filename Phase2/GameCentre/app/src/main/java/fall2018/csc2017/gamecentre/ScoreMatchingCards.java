package fall2018.csc2017.gamecentre;

import android.support.annotation.NonNull;

public class ScoreMatchingCards extends ScoreAbstract implements Comparable<ScoreMatchingCards> {

    /**
     * Creates a ScoreMatchingCards where username comes from user (Used to create score at the endgame)
     */
    public ScoreMatchingCards(int userScore, User user) {
        super(userScore, user);
    }

    /**
     * Creates a ScoreMatchingCards where username comes from a string
     */
    public ScoreMatchingCards(long _id, int userScore, String userName) {
        super(_id, userScore, userName);
    }

    @Override
    public int compareTo(@NonNull ScoreMatchingCards comparedToScore) {
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
        } else if (!(comparedToObject instanceof ScoreMatchingCards)) {
            return false;
        } else {
            ScoreMatchingCards comparedToScore = (ScoreMatchingCards) comparedToObject;
            return (this.getUserName().equals(comparedToScore.getUserName()) &&
                    (this.getUserScore() == comparedToScore.getUserScore()));
        }
    }
}
