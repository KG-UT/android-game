package fall2018.csc2017.gamecentre;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

<<<<<<< HEAD
import fall2018.csc2017.gamecentre.Games.SlidingTile.SlidingTileStartingActivity;
||||||| merged common ancestors
import fall2018.csc2017.gamecentre.SlidingTile.SlidingTileStartingActivity;
=======
import fall2018.csc2017.gamecentre.slidingTile.SlidingTileStartingActivity;
>>>>>>> master

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
    abstract public void addOtherScoreboardButtonListener();

    /**
     * Add a listener to the other scoreboard button
     */
    abstract public void addStartingActivityButtonListener();

    /**
     * Switches to other scoreboard.
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
    abstract public void scoreBoardView();

    /**
     * Sets scoreboard.
     *
     * @return the scoreboard
     */
    abstract public Scoreboard setupScoreboard();
}
