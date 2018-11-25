package fall2018.csc2017.gamecentre;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static fall2018.csc2017.gamecentre.LoginActivity.myUser;

//Adapted from https://stacktips.com/tutorials/android/android-listview-tutorial

/**
 * The general sliding tile scoreboard activity class
 */
public class ScoreboardGameUserActivity extends ScoreboardActivity {

    /**
     * Determines if the scoreboard is the game scoreboard or the user scoreboard
     */
    private boolean isGameScoreboard = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_tiles_game_scoreboard_display);

        scoreBoardView();
        scoreBoardTitleView();

        addOtherScoreboardButtonListener();
    }

    /**
     * Add other scoreboard button listener.
     */
    protected void addOtherScoreboardButtonListener() {
        Button newButton = findViewById(R.id.slidingTilesToUserScoreboardButton);
        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToOtherScoreboard();
            }
        });
    }

    /**
     * Changes the state of the scoreboard between the game scoreboard and the user scoreboard.
     */
     private void switchToOtherScoreboard() {
         isGameScoreboard = !isGameScoreboard;
         scoreBoardTitleView();
         scoreBoardView();
    }

    /**
     * Displays the scoreboard.
     */
    protected void scoreBoardView() {
        ListView ScoreboardListView;
        ArrayAdapter arrayAdapter;

        ScoreboardListView = findViewById(R.id.slidingTilesGameListViewer);

        arrayAdapter = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, setupCombinedScoreboard());
        ScoreboardListView.setAdapter(arrayAdapter);
    }

    /**
     * Displays the scoreboard title.
     */
    protected void scoreBoardTitleView() {
        TextView scoreBoardTitle = findViewById(R.id.slidingTilesGameScoreboardTitleTextView);
        if (isGameScoreboard) {
            String scoreBoardTitleText = "General Scoreboard";
            scoreBoardTitle.setText(scoreBoardTitleText);
        } else {
            String scoreBoardTitleText = "Personal Scoreboard";
            scoreBoardTitle.setText(scoreBoardTitleText);
        }
    }

    /**
     * Sets up the combined scoreboard for all games.
     *
     * @return the scoreboard in a list of strings form
     */
    private List<String> setupCombinedScoreboard() {
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
     * Sets up the the scoreboard for a game.
     *
     * @return the scoreboard
     */
    private Scoreboard setupScoreboardForGame(List<Score> listOfScores, String nameOfGame) {
        Scoreboard newScoreboard = new Scoreboard(listOfScores, nameOfGame);
        newScoreboard.organizeScoreBoard();
        return newScoreboard;
    }

    /**
     * Gets all the sliding tile scores from the database.
     *
     * @return the list of all sliding tile scores.
     */
    private List<Score> getSlidingTilesScoresFromDatabase() {
        if (isGameScoreboard) {
            List<User> listOfUsers = databaseTools.getAllUsers();
            List<Score> listOfScoresSlidingTiles = new ArrayList<>();
            for (User user : listOfUsers) {
                listOfScoresSlidingTiles.addAll(databaseTools.getUserSlidingTileScores(user.getUsername()));
            }
            return listOfScoresSlidingTiles;
        } else {
            return databaseTools.getUserSlidingTileScores(myUser.getUsername());
        }
    }

    /**
     * Gets all the TicTacToe scores from the database.
     *
     * @return the list of all TicTacToe scores.
     */
    private List<Score> getTicTacToeScoresFromDatabase() {
        if (isGameScoreboard) {
            List<User> listOfUsers = databaseTools.getAllUsers();
            List<Score> listOfScoresSlidingTiles = new ArrayList<>();
            for (User user : listOfUsers) {
                listOfScoresSlidingTiles.addAll(databaseTools.getUserSlidingTileScores(user.getUsername()));
            }
            return listOfScoresSlidingTiles;
        } else {
            return databaseTools.getUserSlidingTileScores(myUser.getUsername());
        }
    }

    /**
     * Gets all the Go scores from the database.
     *
     * @return the list of all Go scores.
     */
    private List<Score> getGoScoresFromDatabase() {
        if (isGameScoreboard) {
            List<User> listOfUsers = databaseTools.getAllUsers();
            List<Score> listOfScoresSlidingTiles = new ArrayList<>();
            for (User user : listOfUsers) {
                listOfScoresSlidingTiles.addAll(databaseTools.getUserSlidingTileScores(user.getUsername()));
            }
            return listOfScoresSlidingTiles;
        } else {
            return databaseTools.getUserSlidingTileScores(myUser.getUsername());
        }
    }
}