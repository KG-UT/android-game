package fall2018.csc2017.gamecentre.slidingTile;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import fall2018.csc2017.gamecentre.R;
import fall2018.csc2017.gamecentre.Score;
import fall2018.csc2017.gamecentre.ScoreboardActivity;

import static fall2018.csc2017.gamecentre.LoginActivity.myUser;

// Adapted from https://stacktips.com/tutorials/android/android-listview-tutorial

/**
 * The Scoreboard activity for a user
 */
public class ScoreboardUserActivity extends ScoreboardActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_tiles_user_scoreboard_display);

        scoreBoardView(R.id.userListViewer1, setupCombinedScoreboard());
        addOtherScoreboardButtonListener(R.id.toGeneralButton);
        addStartingActivityButtonListener(R.id.startingActivityButton1);
    }

    /**
     * Switch to the ScoreboardUserActivity
     */
    public void switchToOtherScoreboard() {
        Intent tmp = new Intent(this, ScoreboardGameActivity.class);
        startActivity(tmp);
    }

    /**
     * Sets up the combined scoreboard for all games (extend with this for new games).
     *
     * @return the scoreboard
     */
    public List<String> setupCombinedScoreboard() {
        List<String> scoreBoardListData = new ArrayList<>();
        scoreBoardListData.addAll(setupScoreboardForGame(getSlidingTilesScoresFromDatabase(),
                "Sliding Tiles").getScoreBoardDataStringForm());
        scoreBoardListData.addAll(setupScoreboardForGame(getTicTacToeScoresFromDatabase(),
                "TicTacToe").getScoreBoardDataStringForm());
        scoreBoardListData.addAll(setupScoreboardForGame(getGoScoresFromDatabase(),
                "Go").getScoreBoardDataStringForm());

        return scoreBoardListData;
    }

    /**
     * Gets all the sliding tile scores from the database.
     *
     * @return the list of all sliding tile scores.
     */
    protected List<Score> getSlidingTilesScoresFromDatabase() {
        return databaseTools.getUserSlidingTileScores(myUser.getUsername());
    }

    /**
     * Gets all the TicTacToe scores from the database.
     *
     * @return the list of all TicTacToe scores.
     */
    protected List<Score> getTicTacToeScoresFromDatabase() {
        return databaseTools.getUserSlidingTileScores(myUser.getUsername());
    }

    /**
     * Gets all the Go scores from the database.
     *
     * @return the list of all Go scores.
     */
    protected List<Score> getGoScoresFromDatabase() {
        return databaseTools.getUserSlidingTileScores(myUser.getUsername());
    }
}
