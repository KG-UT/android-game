package fall2018.csc2017.gamecentre;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;

import fall2018.csc2017.gamecentre.games.slidingTile.SlidingTileActivity;

import fall2018.csc2017.gamecentre.abstractClasses.BoardManager;


/**
 * The class responsible for displaying the saved games view.
 */
public class SavedGamesView extends AppCompatActivity {

    /**
     * Board manager associated with this activity.
     *
     * Future functionality
     */
    private BoardManager boardManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_games_view);
    }

    /**
     * Adds a listener for saveGameButton1.
     *
     * Future functionality
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
