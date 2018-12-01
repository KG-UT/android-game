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

    /**
     * Board manager associated with this activity.
     */
    private BoardManager boardManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_games_view);
    }

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
}
