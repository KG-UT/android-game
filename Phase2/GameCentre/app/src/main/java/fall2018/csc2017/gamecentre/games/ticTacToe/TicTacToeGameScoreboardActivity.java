package fall2018.csc2017.gamecentre.games.ticTacToe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import fall2018.csc2017.gamecentre.DBTools;
import fall2018.csc2017.gamecentre.R;
import fall2018.csc2017.gamecentre.Score;
import fall2018.csc2017.gamecentre.Scoreboard;
import fall2018.csc2017.gamecentre.ScoreboardActivity;
import fall2018.csc2017.gamecentre.database.entity.User;
import fall2018.csc2017.gamecentre.games.slidingTile.SlidingTileUserScoreboardActivity;

//Adapted from https://stacktips.com/tutorials/android/android-listview-tutorial

/**
 * The general sliding tile scoreboard activity class
 */
public class TicTacToeGameScoreboardActivity extends ScoreboardActivity {

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
     public void addOtherScoreboardButtonListener() {
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
    public void addStartingActivityButtonListener() {
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
     public void switchToOtherScoreboard() {
        Intent tmp = new Intent(this, SlidingTileUserScoreboardActivity.class);
        startActivity(tmp);
    }

    /**
     * Creates the view for the scoreboard.
     */
    public void scoreBoardView() {
        ListView ScoreboardListView;
        ArrayAdapter arrayAdapter;
        List<String> scoreBoardListData = setupScoreboard().getScoreBoardDataStringForm();
        ScoreboardListView = findViewById(R.id.userListViewer);

        arrayAdapter = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, scoreBoardListData);
        ScoreboardListView.setAdapter(arrayAdapter);
    }

    /** TODO: Refactor to reflect repo work.
     * Sets up the scoreboard for Sliding Tile.
     *
     * @return the fully set up scoreboard
     */
     public Scoreboard setupScoreboard() {
         DBTools database = new DBTools(this);
//        List<User> listOfUsers = database.getAllUsers();
        List<Score> listOfScores = new ArrayList<>();

//        for (User user: listOfUsers) {
//            listOfScores.addAll(database.getUserSlidingTileScores(user.getUsername()));
//        }

        Scoreboard newScoreboard = new Scoreboard(listOfScores);
        newScoreboard.organizeScoreBoard();
        return newScoreboard;
    }
}