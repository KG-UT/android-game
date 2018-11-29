package fall2018.csc2017.gamecentre;

import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * The Score class which is used in the score board (Just a placeholder for now).
 */
public class Score implements Comparable<Score>, Serializable {

    /**
     * The Score's id in the database.
     */
    private long _id;

    /**
     * A string that contains the username of the user that got this score
     */
    private String userName;

    /**
     * An int that represents the score that the user got
     */
    private int userScore;

    /**
     * Creates a score where username comes from user (Used to create score at the endgame)
     */
    public Score(int userScore, User user) {
        this._id = 1;
        this.userName = user.getUsername();
        this.userScore = userScore;
    }

    /**
     * Creates a score where username comes from a string
     */
    public Score(long _id, int userScore, String userName) {
        this._id = _id;
        this.userName = userName;
        this.userScore = userScore;
    }

    /**
     * Returns the Score's id.
     * Type of long required for SQLite.
     *
     * @return the id
     */
    public long get_id() {
        return _id;
    }

    /**
     * Sets the Score's id.
     * Type of long required for SQLite.
     *
     * @param _id the id.
     */
    public void set_id(long _id) {
        this._id = _id;
    }

    /**
     * Returns a string of the user that got this score
     */
    public String getUserName() {
        return this.userName;
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
        return getUserName() + ":" + " " + Integer.toString(getUserScore());
    }
}
