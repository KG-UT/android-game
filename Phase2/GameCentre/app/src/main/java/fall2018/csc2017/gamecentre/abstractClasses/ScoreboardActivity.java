package fall2018.csc2017.gamecentre.abstractClasses;

/*
 * Excluded from tests because it is an abstract class
 **/
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
     * Adds a button for the refresher.
     */
    abstract protected void addRefreshButtonListener();

    /**
     * Displays the scoreboard.
     */
    abstract protected void scoreBoardView();

    /**
     * Displays the scoreboard title.
     */
    abstract protected void scoreBoardTitleView();
}
