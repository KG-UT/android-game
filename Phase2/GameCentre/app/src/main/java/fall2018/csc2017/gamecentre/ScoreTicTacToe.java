package fall2018.csc2017.gamecentre;

import android.support.annotation.NonNull;

public class ScoreTicTacToe extends ScoreAbstract implements Comparable<ScoreTicTacToe> {
    /**
     * Creates a ScoreTicTacToe where username comes from a string
     */
    public ScoreTicTacToe(int userScore, String owner) {
        super(userScore, owner);
    }

    @Override
    public int compareTo(@NonNull ScoreTicTacToe comparedToScore) {
        if(comparedToScore.getUserScore() > this.getUserScore()) {
            return 1;
        } else if (comparedToScore.getUserScore() < this.getUserScore()) {
            return -1;
        } else {
            return Integer.compare(comparedToScore.getOwner().compareTo(this.getOwner()), 0);
        }
    }

    @Override
    public boolean equals(Object comparedToObject) {
        if (comparedToObject == null) {
            return false;
        } else if (!(comparedToObject instanceof ScoreTicTacToe)) {
            return false;
        } else {
            ScoreTicTacToe comparedToScore = (ScoreTicTacToe) comparedToObject;
            return (this.getOwner().equals(comparedToScore.getOwner()) &&
                    (this.getUserScore() == comparedToScore.getUserScore()));
        }
    }
}
