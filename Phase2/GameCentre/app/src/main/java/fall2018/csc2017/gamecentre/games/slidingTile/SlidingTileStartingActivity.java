package fall2018.csc2017.gamecentre.games.slidingTile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.Blob;
import com.google.firebase.firestore.DocumentSnapshot;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import fall2018.csc2017.gamecentre.abstractClasses.BoardManager;
import fall2018.csc2017.gamecentre.abstractClasses.GameStartingActivity;
import fall2018.csc2017.gamecentre.R;
import fall2018.csc2017.gamecentre.SavedGamesView;
import fall2018.csc2017.gamecentre.database.GameDatabaseTools;
import fall2018.csc2017.gamecentre.games.ticTacToe.TicTacToeActivity;
import fall2018.csc2017.gamecentre.games.ticTacToe.TicTacToeStartingActivity;
import fall2018.csc2017.gamecentre.scoreboardAndScores.ScoreboardGameUserActivity;
import fall2018.csc2017.gamecentre.view.LoginActivity;

/**
 * The initial activity for the sliding puzzle tile game.
 */
public class SlidingTileStartingActivity extends GameStartingActivity {
    /**
     * The autosave .ser file.
     */
    public static String AUTOSAVE = "autosave.ser";

    /**
     * The main save file.
     */
    public static final String SAVE_FILENAME = "sliding_tile_save_file.ser";

    /**
     * A temporary save file.
     */
    public static final String TEMP_SAVE_FILENAME = "sliding_tile_save_file_tmp.ser";

    /**
     * The board manager.
     */
    private SlidingTileBoardManager slidingTileBoardManager;

    private GameDatabaseTools gameDatabaseTools = new GameDatabaseTools();

    /**
     * The Database methods needed for sliding tiles.
     */
    private GameDatabaseTools slidingTileDatabaseTools = new GameDatabaseTools();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //saveToFile(TEMP_SAVE_FILENAME);
        slidingTileBoardManager = new SlidingTileBoardManager();

        setContentView(R.layout.activity_sliding_tile_starting_);
        addScoreboardButtonListener();
        addNewGameButtonListener();
        addAutoSaveButtonListener();
    }

    /**
     * Add a listener to the scoreboard button
     */
    private void addScoreboardButtonListener() {
        Button scoreboardButton = findViewById(R.id.ScoreboardButton);
        scoreboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToScoreboard();
            }
        });
    }

    /**
     * Add a listener to the new game button
     */
    private void addNewGameButtonListener() {
        Button loadButton = findViewById(R.id.NewGameButton);
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToSettings();
            }
        });
    }

    /**
     * Display that a game was loaded successfully.
     */
    private void makeToastLoadedText() {
        Toast.makeText(this, "Loaded Game", Toast.LENGTH_SHORT).show();
    }

    /**
     * Display that a game was saved successfully.
     */
    private void makeToastSavedText() {
        Toast.makeText(this, "Game Saved", Toast.LENGTH_SHORT).show();
    }

    /**
     * Read the temporary board from disk.
     */
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("TILE", "LOADING FROM FILE.....");
    }

    /**
     * Switch to the SettingsActivity to set the settings of the game.
     */
    private void switchToSettings() {
        Intent tmp = new Intent(this, SlidingTilesSettingsActivity.class);
        startActivity(tmp);
    }

    /**
     * Switch to the SlidingTileGameScoreboardActivity
     */
    private void switchToScoreboard() {
        Intent tmp = new Intent(this, ScoreboardGameUserActivity.class);
        startActivity(tmp);
    }

    /**
     * Load the board manager from fileName.
     */
    private void loadFromFile() {
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        gameDatabaseTools.getSlidingTileBoardManager(user.getUid()).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if(document.exists()) {
                                document.getData().get(user.getUid());
                                byte[] boardManagerBytes = ((Blob) document.getData().get("owner")).toBytes();
                                try {
                                    slidingTileBoardManager = gameDatabaseTools.convertBytesToSlidingTileBoardManager(boardManagerBytes);

                                } catch (Exception e) {
                                    Log.d("TAG", e.getMessage());
                                }
                            } else {
                                slidingTileBoardManager = new SlidingTileBoardManager();
                            }

                            Intent tmp = new Intent(SlidingTileStartingActivity.this, SlidingTileActivity.class);
                            HashMap<String, Object> settings = new HashMap<>();
                            settings.put("PRELOADED_BOARD_MANAGER", slidingTileBoardManager);
                            tmp.putExtra("SETTINGS", settings);

                            startActivity(tmp);
                        }
                    }
                });
    }

    /**
     * Save the board manager to fileName.
     */
    public void saveToFile() {
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        gameDatabaseTools.saveToDatabase(slidingTileBoardManager, user.getUid());
    }

    /**
     * Adds a listener for the AutoSaveButton.
     */
    private void addAutoSaveButtonListener() {
        Button AutoSaveButton = findViewById(R.id.AutoSaveButton);
        AutoSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFromFile();

            }
        });
    }
}
