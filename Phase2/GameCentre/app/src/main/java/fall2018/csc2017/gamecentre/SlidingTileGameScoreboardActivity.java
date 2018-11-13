package fall2018.csc2017.gamecentre;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

//Adapted from https://stacktips.com/tutorials/android/android-listview-tutorial

/**
 * The general sliding tile scoreboard activity class
 */
public class SlidingTileGameScoreboardActivity extends ScoreboardActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_scoreboard_display);
        DBTools database= new DBTools(this);
        scoreBoardView();
        addOtherScoreboardButtonListener();
        addStartingActivityButtonListener();
    }

    /**
     * Add a listener to the other scoreboard button
     */
     void addOtherScoreboardButtonListener() {
        Button userScoreboardButton = findViewById(R.id.toUserButton);
         userScoreboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToOtherScoreboard();
            }
        });
    }

    /**
     * Adds the starting activity button.
     */
    void addStartingActivityButtonListener() {
        Button startingActivityButton = findViewById(R.id.startingActivityButton);
        startingActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToStartingActivity();
            }
        });
    }

    /**
     * Switch to the UserScoreboardActivity
     */
     void switchToOtherScoreboard() {
        Intent tmp = new Intent(this, SlidingTileUserScoreboardActivity.class);
        startActivity(tmp);
    }

    /**
     * Creates the view for the scoreboard.
     */
    void scoreBoardView() {
        ListView ScoreboardListView;
        ArrayAdapter arrayAdapter;
        List<String> scoreBoardListData = setupScoreboard().getScoreBoardDataStringForm();
        ScoreboardListView = findViewById(R.id.userListViewer);

        arrayAdapter = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, scoreBoardListData);
        ScoreboardListView.setAdapter(arrayAdapter);
    }

    /**
     * Sets up the scoreboard for Sliding Tile.
     *
     * @return the fully set up scoreboard
     */
     Scoreboard setupScoreboard() {
         DBTools database = new DBTools(this);
        List<User> listOfUsers = database.getAllUsers();
        List<Score> listOfScores = new ArrayList<>();

        for (User user: listOfUsers) {
            listOfScores.addAll(database.getUserSlidingTileScores(user.getUsername()));
        }

        Scoreboard newScoreboard = new Scoreboard(listOfScores);
        newScoreboard.organizeScoreBoard();
        return newScoreboard;
    }
}