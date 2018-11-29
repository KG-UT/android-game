package fall2018.csc2017.gamecentre.abstractClasses;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
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


