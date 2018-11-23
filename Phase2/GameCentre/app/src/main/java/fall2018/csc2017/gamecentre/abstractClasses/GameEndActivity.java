package fall2018.csc2017.gamecentre.abstractClasses;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * The abstract class for each type of end-of-game activity.
 */
abstract public class GameEndActivity  extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Display the score as a Textview.
     */
    abstract public void displayScore();

    /**
     * Save score to database.
     */
    abstract public void saveScore();

    /**
     * Adds a main menu button listener. (Interact on click)
     */
    abstract public void addMenuButtonListener();

    /**
     * Send us to the main menu
     */
    abstract public void toMenu();
}
