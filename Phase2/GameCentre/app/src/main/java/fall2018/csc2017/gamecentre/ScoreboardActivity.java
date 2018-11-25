package fall2018.csc2017.gamecentre;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import fall2018.csc2017.gamecentre.slidingTile.SlidingTileStartingActivity;

/**
 * The abstract class for Scoreboard activity in general.
 */
public abstract class ScoreboardActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Initializes a database to work with
     */
    protected DBTools databaseTools = new DBTools(this);

    /**
     * Add other scoreboard button listener.
     */
    public void addOtherScoreboardButtonListener(int rIdIntValue) {
        Button newButton = findViewById(rIdIntValue);
        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToOtherScoreboard();
            }
        });
    }

    /**
     * Add a listener to the other scoreboard button
     */
    public void addStartingActivityButtonListener(int rIdIntValue) {
        Button newButton = findViewById(rIdIntValue);
        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToStartingActivity();
            }
        });
    }

    /**
     * Switches to other scoreboard (extend for new types of scoreboards).
     */
    abstract public void switchToOtherScoreboard();

    /**
     * Switch to the SlidingTileStartingActivity
     */
    public void switchToStartingActivity() {
        Intent tmp = new Intent(this, SlidingTileStartingActivity.class);
        startActivity(tmp);
    }

    /**
     * Score board view.
     */
    public void scoreBoardView(int rIdIntValue, List<String> scoreBoardListData) {
        ListView ScoreboardListView;
        ArrayAdapter arrayAdapter;

        ScoreboardListView = findViewById(rIdIntValue);

        arrayAdapter = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, scoreBoardListData);
        ScoreboardListView.setAdapter(arrayAdapter);
    }

    /**
     * Sets up the the scoreboard for a game (extend with this for new games).
     *
     * @return the scoreboard
     */
    public Scoreboard setupScoreboardForGame(List<Score> listOfScores, String nameOfGame) {
        Scoreboard newScoreboard = new Scoreboard(listOfScores, nameOfGame);
        newScoreboard.organizeScoreBoard();
        return newScoreboard;
    }

    /**
     * Sets up the combined scoreboard for all games (extend with this for new games).
     *
     * @return the scoreboard
     */
    abstract public List<String> setupCombinedScoreboard();

    /**
     * Gets all the sliding tile scores from the database.
     *
     * @return the list of all sliding tile scores.
     */
    abstract protected List<Score> getSlidingTilesScoresFromDatabase();

    /**
     * Gets all the TicTacToe scores from the database.
     *
     * @return the list of all TicTacToe scores.
     */
    abstract protected List<Score> getTicTacToeScoresFromDatabase();

    /**
     * Gets all the Go scores from the database.
     *
     * @return the list of all Go scores.
     */
    abstract protected List<Score> getGoScoresFromDatabase();
}
