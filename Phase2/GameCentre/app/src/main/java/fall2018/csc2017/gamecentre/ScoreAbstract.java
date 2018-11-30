package fall2018.csc2017.gamecentre;

import java.io.Serializable;

abstract public class ScoreAbstract implements Serializable {
    /**
     * A string that contains the username of the user that got this score
     */
    private String userName;

    /**
     * An int that represents the score that the user got
     */
    private int userScore;

    /**
     * Creates a ScoreAbstract where username comes from a string
     */
    public ScoreAbstract(int userScore, String owner) {
        this.userScore = userScore;
        this.userName = owner;
    }

    /**
     * Returns a string of the user that got this score
     */
    public String getOwner() {
        return this.userName;
    }

    /**
     * Returns an int that is the user's score
     */
    public int getUserScore() {
        return this.userScore;
    }

    @Override
    abstract public boolean equals(Object comparedToObject);

    @Override
    public String toString() {
        return getOwner() + ":" + " " + Integer.toString(getUserScore());
    }
}
