package fall2018.csc2017.gamecentre;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;

import fall2018.csc2017.gamecentre.games.slidingTile.SlidingTileBoardManager;
import fall2018.csc2017.gamecentre.games.slidingTile.SlidingTileActivity;

import static fall2018.csc2017.gamecentre.LoginActivity.myUser;

/**
 * The class responsible for displaying the saved games view.
 */
public class SavedGamesView extends AppCompatActivity {
    DBTools database= new DBTools(this);
    private SlidingTileBoardManager boardManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_games_view);
        ArrayList<Score> scores = database.getUserSlidingTileScores(myUser.getUsername());
        for(Score s: scores) {
            Log.d("TAG", "" + s.getUserScore());
        }

        addSaveButtonListener1();
    }

    private void addSaveButtonListener1() {
        Button SaveGame1Button = findViewById(R.id.saveGameButton1);
        SaveGame1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFromFile();
                Intent tmp = new Intent(SavedGamesView.this, SlidingTileActivity.class);
                HashMap<String, Object> settings = new HashMap<>();
                settings.put("PRELOADED_BOARD_MANAGER", boardManager);
                tmp.putExtra("SETTINGS", settings);
                startActivity(tmp);
            }
        });
    }

    /**
     * Load the board manager from SAVE_FILE1
     *
     */
    protected void loadFromFile() {

        try {
            FileInputStream obj = new FileInputStream("/data/data/fall2018.csc2017.slidingtiles/files/save_1.ser");
            BufferedInputStream var = new BufferedInputStream(obj);
            ObjectInputStream obj2 = new ObjectInputStream(var);
            boardManager = (SlidingTileBoardManager)obj2.readObject();
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        } catch (ClassNotFoundException e) {
            Log.e("login activity", "File contained unexpected data type: " + e.toString());
        }
    }
}
