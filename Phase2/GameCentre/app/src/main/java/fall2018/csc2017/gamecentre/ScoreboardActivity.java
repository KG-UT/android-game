/**
 * Excluded from tests because it is an abstract class
 **/
package fall2018.csc2017.gamecentre;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

<<<<<<< HEAD
import fall2018.csc2017.gamecentre.games.slidingTile.SlidingTileStartingActivity;

=======
>>>>>>> 5724a0e59e64733cf570630fb408f82c1b49f4f1
/**
 * The abstract class for Scoreboard activity.
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
     * Determines if the scoreboard is the game scoreboard or the user scoreboard
     */
    protected boolean isGameScoreboard = true;

    /**
     * Add other scoreboard button listener.
     */
    abstract protected void addOtherScoreboardButtonListener();

    /**
     * Displays the scoreboard.
     */
    abstract protected void scoreBoardView();

    /**
     * Displays the scoreboard title.
     */
    abstract protected void scoreBoardTitleView();
}
