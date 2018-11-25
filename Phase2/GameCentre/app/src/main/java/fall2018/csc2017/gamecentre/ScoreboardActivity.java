package fall2018.csc2017.gamecentre;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

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
