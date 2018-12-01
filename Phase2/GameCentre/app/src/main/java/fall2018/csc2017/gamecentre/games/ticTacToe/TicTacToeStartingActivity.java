package fall2018.csc2017.gamecentre.games.ticTacToe;

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

import java.util.HashMap;

import fall2018.csc2017.gamecentre.abstractClasses.GameStartingActivity;
import fall2018.csc2017.gamecentre.R;
import fall2018.csc2017.gamecentre.database.GameDatabaseTools;
import fall2018.csc2017.gamecentre.scoreboardAndScores.ScoreboardGameUserActivity;

import fall2018.csc2017.gamecentre.view.LoginActivity;

/**
 * The initial activity for the sliding puzzle tile game.
 */
public class TicTacToeStartingActivity extends GameStartingActivity {
    /**
     * The board manager.
     */
    private TicTacToeBoardManager boardManager;

    /**
     * The database
     */
    private GameDatabaseTools gameDatabaseTools;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boardManager = new TicTacToeBoardManager(4, 4);
        gameDatabaseTools = new GameDatabaseTools();

        setContentView(R.layout.activity_tic_tac_toe_starting);
        addNewGameButtonListener();
        addScoreboardButtonListener();
        addAutoSaveButtonListener();

    }

    /**
     * Add a listener to the scoreboard button
     */
    private void addScoreboardButtonListener() {
        Button scoreboardButton = findViewById(R.id.TicTacToeScoreboardButton);
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
        Button loadButton = findViewById(R.id.ticTacToeNewGameButton);
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeToastLoadedText();
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
     *
     * Future functionality
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
        Intent tmp = new Intent(this, TicTacToeSettingsActivity.class);
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
     * Loads a saved game.
     */
    private void loadSavedGame() {
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        gameDatabaseTools.getTicTacToeBoardManager(user.getUid()).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if(document.exists()) {
                                document.getData().get(LoginActivity.currentUser);
                                byte[] boardManagerBytes = ((Blob) document.getData().get("owner")).toBytes();
                                try {
                                    boardManager = gameDatabaseTools.convertBytesToTicTacToeBoardManager(boardManagerBytes);
                                } catch (Exception e) {
                                    Log.d("TAG", e.getMessage());
                                }
                            }
                            Intent tmp = new Intent(TicTacToeStartingActivity.this, TicTacToeActivity.class);

                            HashMap<String, Object> settings = new HashMap<>();
                            settings.put("PRELOADED_BOARD_MANAGER", boardManager);
                            tmp.putExtra("SETTINGS", settings);

                            startActivity(tmp);
                        }
                    }
                });
    }

    /**
     * Adds a listener for the AutoSaveButton.
     */
    private void addAutoSaveButtonListener() {
        Button AutoSaveButton = findViewById(R.id.TicTacToeAutosaveButton);
        AutoSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadSavedGame();
            }
        });
    }
}
