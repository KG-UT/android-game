package fall2018.csc2017.gamecentre.games.slidingTile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import fall2018.csc2017.gamecentre.DBTools;
import fall2018.csc2017.gamecentre.R;
import fall2018.csc2017.gamecentre.Score;
import fall2018.csc2017.gamecentre.ScoreSlidingTiles;
import fall2018.csc2017.gamecentre.Scoreboard;
import fall2018.csc2017.gamecentre.ScoreboardSlidingTies;
import fall2018.csc2017.gamecentre.abstractClasses.ScoreboardActivity;


// Adapted from https://stacktips.com/tutorials/android/android-listview-tutorial

/**
 * The Scoreboard activity for a user
 */
public class SlidingTileUserScoreboardActivity extends ScoreboardActivity {

    /**
     * Initializes a database to work with
     */
    private DBTools databaseTools = new DBTools(SlidingTileUserScoreboardActivity.this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_scoreboard_display);

        scoreBoardView();
        addOtherScoreboardButtonListener();
        addStartingActivityButtonListener();
    }

    /**
     * Add a listener to the gameScoreboardButton button
     */
    public void addOtherScoreboardButtonListener() {
        Button gameScoreboardButton = findViewById(R.id.toGeneralButton);
        gameScoreboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToOtherScoreboard();
            }
        });
    }

    /**
     * Add a listener to the startingActivityButton button
     */
    public void addStartingActivityButtonListener() {
        Button startingActivityButton1 = findViewById(R.id.startingActivityButton1);
        startingActivityButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Not available on my branch?
//                switchToStartingActivity();
            }
        });
    }

    /**
     * Switch to the SlidingTileUserScoreboardActivity
     */
    public void switchToOtherScoreboard() {
        Intent tmp = new Intent(this, SlidingTileGameScoreboardActivity.class);
        startActivity(tmp);
    }

    /**
     * Displays and passes data into the scoreboard
     */
    public void scoreBoardView() {
        ListView ScoreboardListView;
        ArrayAdapter arrayAdapter;
        List<String> scoreBoardListData = setupScoreboard().getScoreBoardDataStringForm();
        ScoreboardListView = findViewById(R.id.userListViewer1);

        arrayAdapter = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, scoreBoardListData);
        ScoreboardListView.setAdapter(arrayAdapter);
    }

    @Override
    protected void scoreBoardTitleView() {

    }

    /**
     * Returns a new sorted scoreboard that is filled with every user's scores
     */
    public Scoreboard setupScoreboard() {
        List<ScoreSlidingTiles> listOfScores = databaseTools.getUserSlidingTileScores("temp");
        Scoreboard newScoreboard = new ScoreboardSlidingTies(listOfScores, "TEMP");
        newScoreboard.organizeScoreBoard();
        return newScoreboard;
    }
}