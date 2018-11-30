package fall2018.csc2017.gamecentre;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.HashMap;

import fall2018.csc2017.gamecentre.games.slidingTile.SlidingTileActivity;

import fall2018.csc2017.gamecentre.abstractClasses.BoardManager;
import fall2018.csc2017.gamecentre.scoreboardAndScores.scores.ScoreSlidingTiles;

import static fall2018.csc2017.gamecentre.view.LoginActivity.currentUser;


/**
 * The class responsible for displaying the saved games view.
 */
public class SavedGamesView extends AppCompatActivity {
    DBTools database= new DBTools(this);
    private BoardManager boardManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_games_view);
        ArrayList<ScoreSlidingTiles> scores = database.getUserSlidingTileScores(currentUser.getEmail());
        for(ScoreSlidingTiles s: scores) {
            Log.d("TAG", "" + s.getUserScore());
        }
//        ArrayList<Score> scores = database.getUserSlidingTileScores(currentUser.getEmail());
//        for(Score s: scores) {
//            Log.d("TAG", "" + s.getUserScore());
//        }
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_saved_games_view);
////        ArrayList<Score> scores = database.getUserSlidingTileScores(myUser.getUsername());
////        for(Score s: scores) {
////            Log.d("TAG", "" + s.getUserScore());
//        }
//
//        addSaveButtonListener1();
////    }

    /**
     * Adds a listener for saveGameButton1.
     */
    private void addSaveButtonListener1() {
        Button SaveGame1Button = findViewById(R.id.saveGameButton1);
        SaveGame1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                loadFromFile();
                Intent tmp = new Intent(SavedGamesView.this, SlidingTileActivity.class);
                HashMap<String, Object> settings = new HashMap<>();
                settings.put("PRELOADED_BOARD_MANAGER", boardManager);
                tmp.putExtra("SETTINGS", settings);
                startActivity(tmp);
            }
        });
    }

    /** TODO: Reimplement this properly with database
     * Load the board manager from SAVE_FILE1
     *
     */
//    protected void loadFromFile() {
//        try {
//            FileInputStream obj = new FileInputStream("/data/data/fall2018.csc2017.slidingtiles/files/save_1.ser");
//            BufferedInputStream var = new BufferedInputStream(obj);
//            ObjectInputStream obj2 = new ObjectInputStream(var);
//
//        } catch (FileNotFoundException e) {
//            Log.e("login activity", "File not found: " + e.toString());
//        } catch (IOException e) {
//            Log.e("login activity", "Can not read file: " + e.toString());
//        } catch (ClassNotFoundException e) {
//            Log.e("login activity", "File contained unexpected data type: " + e.toString());
//        }
//    }
}
