package fall2018.csc2017.gamecentre.abstractClasses;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import java.util.Observable;
import java.util.Observer;

/**
 * The abstract class for every given game type's activities.
 */
abstract public class GameActivity extends AppCompatActivity implements Observer {

    /**
     * Displays the game to the user.
     */
    abstract public void display();

    /**
     * Loads the game activity associated with a given file path.
     *
     * @param fileName the file path.
     */
    abstract protected void loadFromFile(String fileName);

    /**
     * Saves the current game activity state.
     *
     * @param fileName the file path.
     */
    abstract public void saveToFile(String fileName);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void update(Observable o, Object arg) { display(); }




}
