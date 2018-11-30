package fall2018.csc2017.gamecentre.abstractClasses;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Observable;
import java.util.Observer;

/**
 * Excluded from tests because it is an abstract class
 **/
abstract public class GameActivity extends AppCompatActivity implements Observer {

    /**
     * Displays the game to the user.
     */
    abstract public void display();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void update(Observable o, Object arg) {
        display();
    }

}


