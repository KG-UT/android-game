package fall2018.csc2017.gamecentre.slidingTile;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import fall2018.csc2017.gamecentre.R;
import fall2018.csc2017.gamecentre.Score;
import fall2018.csc2017.gamecentre.ScoreboardActivity;
import fall2018.csc2017.gamecentre.User;

//Adapted from https://stacktips.com/tutorials/android/android-listview-tutorial

/**
 * The general sliding tile scoreboard activity class
 */
public class ScoreboardGameActivity extends ScoreboardActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_tiles_game_scoreboard_display);

        scoreBoardView(R.id.slidingTilesGameListViewer, setupCombinedScoreboard());
        addOtherScoreboardButtonListener(R.id.slidingTilesToUserScoreboardButton);
        addStartingActivityButtonListener(R.id.slidingTilesStartingActivityButton);
    }

    /**
     * Switch to the ScoreboardUserActivity (extend for new types of scoreboards)
     */
     public void switchToOtherScoreboard() {
        Intent tmp = new Intent(this, ScoreboardUserActivity.class);
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
        List<User> listOfUsers = databaseTools.getAllUsers();
        List<Score> listOfScoresSlidingTiles = new ArrayList<>();
        for (User user: listOfUsers) {
            listOfScoresSlidingTiles.addAll(databaseTools.getUserSlidingTileScores(user.getUsername()));
        }

        return listOfScoresSlidingTiles;
    }

    /**
     * Gets all the TicTacToe scores from the database.
     *
     * @return the list of all TicTacToe scores.
     */
    protected List<Score> getTicTacToeScoresFromDatabase() {
        List<User> listOfUsers = databaseTools.getAllUsers();
        List<Score> listOfScoresSlidingTiles = new ArrayList<>();
        for (User user: listOfUsers) {
            listOfScoresSlidingTiles.addAll(databaseTools.getUserSlidingTileScores(user.getUsername()));
        }

        return listOfScoresSlidingTiles;
    }

    /**
     * Gets all the Go scores from the database.
     *
     * @return the list of all Go scores.
     */
    protected List<Score> getGoScoresFromDatabase() {
        List<User> listOfUsers = databaseTools.getAllUsers();
        List<Score> listOfScoresSlidingTiles = new ArrayList<>();
        for (User user: listOfUsers) {
            listOfScoresSlidingTiles.addAll(databaseTools.getUserSlidingTileScores(user.getUsername()));
        }

        return listOfScoresSlidingTiles;
    }
}