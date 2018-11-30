package fall2018.csc2017.gamecentre.abstractClasses;

import java.io.Serializable;

import fall2018.csc2017.gamecentre.User;

/**
 * The abstract class of a score.
 */
abstract public class ScoreAbstract implements Serializable {

    /**
     * The ScoreAbstract's id in the database.
     */
    private long _id;

    /**
     * A string that contains the username of the user that got this score.
     */
    private String userName;

    /**
     * An int that represents the score that the user got.
     */
    private int userScore;

    /**
     * Creates a ScoreAbstract where username comes from user (Used to create score at the endgame).
     *
     * @param userScore the int value of a score
     * @param user the user that got the score
     */
    public ScoreAbstract(int userScore, User user) {
        this._id = 1;
        this.userName = user.getUsername();
        this.userScore = userScore;
    }

    /**
     * Creates a ScoreAbstract where username comes from a string.
     *
     * @param _id the long id value of a score
     * @param userScore the int value of the score
     * @param userName the string value of the username of the person that got the score
     */
    public ScoreAbstract(long _id, int userScore, String userName) {
        this._id = _id;
        this.userName = userName;
        this.userScore = userScore;
    }

    /**
     * Returns the ScoreAbstract's id.
     * Type of long required for SQLite.
     *
     * @return the id
     */
    public long get_id() {
        return _id;
    }

    /**
     * Sets the ScoreAbstract's id.
     * Type of long required for SQLite.
     *
     * @param _id the id.
     */
    public void set_id(long _id) {
        this._id = _id;
    }

    /**
     * Returns a string of the user that got this score
     *
     * @return the username of the user associated with this score
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * Returns an int that is the user's score
     *
     * @return the score associated with this score
     */
    public int getUserScore() {
        return this.userScore;
    }

    @Override
    abstract public boolean equals(Object comparedToObject);

    @Override
    public String toString() {
        return getUserName() + ":" + " " + Integer.toString(getUserScore());
    }
}
