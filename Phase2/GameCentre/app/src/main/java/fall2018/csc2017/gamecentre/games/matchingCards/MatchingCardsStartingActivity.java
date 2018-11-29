package fall2018.csc2017.gamecentre.games.matchingCards;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import fall2018.csc2017.gamecentre.R;
import fall2018.csc2017.gamecentre.ScoreboardGameUserActivity;
import fall2018.csc2017.gamecentre.abstractClasses.GameStartingActivity;
import fall2018.csc2017.gamecentre.games.slidingTile.SlidingTileBoardManager;

public class MatchingCardsStartingActivity extends GameStartingActivity {

    /**
     * The board manager.
     */
    private MatchingCardsBoardManager boardManager;
    /**
     * The autosave .ser file.
     */
    public static final String SAVE_FILENAME = "Matching_Cards_autosave_file.ser";
    /**
     * A temporary save file.
     */
    public static final String TEMP_SAVE_FILENAME_1 = "Matching_Cards_save_file_tmp.ser";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boardManager = new MatchingCardsBoardManager();
        setContentView(R.layout.activity_matching_starting);
        addNewGameButtonListener();
        addAutoSaveButtonListener();
        addScoreboardButtonListener();
    }

    /**
     * Read the temporary board from disk.
     */
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("TILE", "LOADING FROM FILE.....");
        loadFromFile(TEMP_SAVE_FILENAME_1);
    }


    /**
     * Save the board manager to fileName.
     *
     * @param fileName the name of the file
     */
    public void saveToFile(String fileName) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    this.openFileOutput(fileName, MODE_PRIVATE));
            outputStream.writeObject(boardManager);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    /**
     * Load the board manager from fileName.
     *
     * @param fileName the name of the file
     */
    private void loadFromFile(String fileName) {
        try {
            InputStream inputStream = this.openFileInput(fileName);
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                boardManager = (MatchingCardsBoardManager) input.readObject();
                inputStream.close();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        } catch (ClassNotFoundException e) {
            Log.e("login activity", "File contained unexpected data type: " + e.toString());
        }
    }

    /**
     * Add a button listener for the Matching Cards game option
     */
    private void addNewGameButtonListener(){
        Button newGameButton = findViewById(R.id.MatchingStartButton);
        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tmp = new Intent(MatchingCardsStartingActivity.this, MatchingCardsActivity.class);
                startActivity(tmp);
            }
        });
    }

    /**
     * Switch to the SlidingTileGameScoreboardActivity
     */
    private void switchToScoreboard() {
        Intent tmp = new Intent(this, ScoreboardGameUserActivity.class);
        startActivity(tmp);
    }

    private void addAutoSaveButtonListener() {
        Button AutoSaveButton = findViewById(R.id.MatchingSaveButton);
        AutoSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFromFile(SAVE_FILENAME);
                Intent tmp = new Intent(MatchingCardsStartingActivity.this, MatchingCardsActivity.class);

                HashMap<String, Object> settings = new HashMap<>();
                settings.put("PRELOADED_BOARD_MANAGER", boardManager);
                tmp.putExtra("SETTINGS", settings);

                startActivity(tmp);

            }
        });
    }

    /**
     * Add a listener to the scoreboard button
     */
    private void addScoreboardButtonListener() {
        Button scoreboardButton = findViewById(R.id.MatchingScoreButton);
        scoreboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToScoreboard();
            }
        });
    }


}
