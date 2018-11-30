package fall2018.csc2017.gamecentre;

import android.support.annotation.NonNull;

public class ScoreMatchingCards extends ScoreAbstract implements Comparable<ScoreMatchingCards> {
    /**
     * Creates a ScoreMatchingCards where username comes from a string
     */
    public ScoreMatchingCards(int userScore, String userName) {
        super(userScore, userName);
    }

    @Override
    public int compareTo(@NonNull ScoreMatchingCards comparedToScore) {
        if(comparedToScore.getUserScore() < this.getUserScore()) {
            return 1;
        } else if (comparedToScore.getUserScore() > this.getUserScore()) {
            return -1;
        } else {
            return Integer.compare(comparedToScore.getOwner().compareTo(this.getOwner()), 0);
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
            return (this.getOwner().equals(comparedToScore.getOwner()) &&
                    (this.getUserScore() == comparedToScore.getUserScore()));
        }
    }
}
