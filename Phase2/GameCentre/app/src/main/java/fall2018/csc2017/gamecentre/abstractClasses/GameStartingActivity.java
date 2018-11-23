package fall2018.csc2017.gamecentre.abstractClasses;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * The abstract class for any given game's starting activity.
 */
abstract public class GameStartingActivity extends AppCompatActivity {

    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * Saves the current state.
     *
     * @param fileName the file path.
     */
    abstract public void saveToFile(String fileName);
}
