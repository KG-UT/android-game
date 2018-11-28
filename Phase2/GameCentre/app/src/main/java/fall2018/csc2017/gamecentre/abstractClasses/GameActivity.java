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

    /**
     * Saves the current game activity state.
     */
    abstract public void saveToDatabase();

    abstract public void retrieveDeSerializedBoardManager(String gameKeyValue);

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

    abstract public String serializeBoardManager();

    abstract public BoardManager deSerializeBoardManager(String serializedBoardManager);

}


