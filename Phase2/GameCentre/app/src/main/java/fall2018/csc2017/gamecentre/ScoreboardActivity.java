package fall2018.csc2017.gamecentre;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * The abstract class for Scoreboard activity in general.
 */
public abstract class ScoreboardActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Add other scoreboard button listener.
     */
    abstract void addOtherScoreboardButtonListener();

    /**
     * Add a listener to the other scoreboard button
     */
    abstract void addStartingActivityButtonListener();

    /**
     * Switches to other scoreboard.
     */
    abstract void switchToOtherScoreboard();

    /**
     * Switch to the SlidingTileStartingActivity
     */
    void switchToStartingActivity() {
        Intent tmp = new Intent(this, SlidingTileStartingActivity.class);
        startActivity(tmp);
    }

    /**
     * Score board view.
     */
    abstract void scoreBoardView();

    /**
     * Sets scoreboard.
     *
     * @return the scoreboard
     */
    abstract Scoreboard setupScoreboard();
}
