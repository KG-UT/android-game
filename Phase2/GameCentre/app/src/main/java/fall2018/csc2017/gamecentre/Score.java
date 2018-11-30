package fall2018.csc2017.gamecentre;

import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * The Score class which is used in the score board (Just a placeholder for now).
 */
public class Score implements Comparable<Score>, Serializable {

    /**
     * A string that contains the username of the user that got this score
     */
    private String owner;

    /**
     * An int that represents the score that the user got
     */
    private int userScore;

    /**
     * Initializes a Score.
     *
     * @param userScore the user's score.
     * @param owner the owner of this score.
     */
    public Score(int userScore, String owner) {
        this.userScore = userScore;
        this.owner = owner;
    }

    /**
     * Returns a string of the user that got this score
     */
    public String getOwner() {
        return this.owner;
    }

    /**
     * Returns an int that is the user's score
     */
    public int getUserScore() {
        return this.userScore;
    }

    @Override
    public int compareTo(@NonNull Score comparedToScore) {
        if(comparedToScore.getUserScore() > this.getUserScore()) {
            return 1;
        } else if (comparedToScore.getUserScore() < this.getUserScore()) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return getOwner() + ":" + " " + Integer.toString(getUserScore());
    }
}
