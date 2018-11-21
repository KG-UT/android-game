package fall2018.csc2017.gamecentre.games.SlidingTile;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

import fall2018.csc2017.gamecentre.database.entity.SlidingTileBoardManager;
import fall2018.csc2017.gamecentre.game.Board;
import fall2018.csc2017.gamecentre.CustomAdapter;
import fall2018.csc2017.gamecentre.GameActivity;
import fall2018.csc2017.gamecentre.GestureDetectGridView;
import fall2018.csc2017.gamecentre.R;

/**
 * The game activity.
 */
public class SlidingTileActivity extends GameActivity {
    /**
     * The autosave .ser file.
     */
    public static final String SAVE_FILE_1 = "sliding_tile_save_file.ser";

    /**
     * The board manager.
     */
    private SlidingTileBoardManager boardManager;

    /**
     * The buttons to display.
     */
    private ArrayList<Button> tileButtons;

    /**
     * Constants for swiping directions. Should be an enum, probably.
     */
    public static final int UP = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;
    public static final int RIGHT = 4;

    // Grid View and calculated column height and width based on device size
    private GestureDetectGridView gridView;
    private static int columnWidth, columnHeight;

    public HashMap<String, Object> getSettings() {
        return (HashMap<String, Object>) getIntent().getSerializableExtra("SETTINGS");
    }

    /**
     * Set up the background image for each button based on the master list
     * of positions, and then call the adapter to set the view.
     */
    // Display
    public void display() {
        updateTileButtons();
        updateScoreText();
        gridView.setAdapter(new CustomAdapter(tileButtons, columnWidth, columnHeight));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        HashMap<String, Object> settings = getSettings();
        boardManager = (SlidingTileBoardManager) settings.get("PRELOADED_BOARD_MANAGER");
        if(boardManager == null) {
            int numRows = (int) settings.get("NUM_ROWS");
            int numCols = (int) settings.get("NUM_COLS");
            boardManager = new SlidingTileBoardManager(numRows, numCols);
        }

        createTileButtons(this);
        setContentView(R.layout.activity_main);

        // Add View to activity
        gridView = findViewById(R.id.grid);
        gridView.setNumColumns(Board.getNumCols());
        gridView.setBoardManager(boardManager);
        boardManager.getBoard().addObserver(this);
        // Observer sets up desired dimensions as well as calls our display function
        gridView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        gridView.getViewTreeObserver().removeOnGlobalLayoutListener(
                                this);
                        int displayWidth = gridView.getMeasuredWidth();
                        int displayHeight = gridView.getMeasuredHeight();

                        columnWidth = displayWidth / Board.getNumCols();
                        columnHeight = displayHeight / Board.getNumRows();

                        display();
                    }
                });
        updateUndosLeftText();
        addUndoMoveButtonListener();
        addSave1ButtonListener();
    }

    /**
     * Create the buttons for displaying the tiles.
     *
     * @param context the context
     */
    private void createTileButtons(Context context) {
        SlidingTileBoard board = boardManager.getBoard();
        tileButtons = new ArrayList<>();
        for (int row = 0; row != Board.getNumRows(); row++) {
            for (int col = 0; col != Board.getNumCols(); col++) {
                Button tmp = new Button(context);
                tmp.setText(board.getTile(row, col).getDisplayNumber());
                tmp.setTextSize(64);
                tmp.setBackgroundColor(Color.parseColor("#ffffff"));
                this.tileButtons.add(tmp);
            }
        }
    }

    /**
     * Update the backgrounds on the buttons to match the tiles.
     */
    private void updateTileButtons() {
        SlidingTileBoard board = boardManager.getBoard();
        int nextPos = 0;
        for (Button b : tileButtons) {
            int row = nextPos / Board.getNumRows();
            int col = nextPos % Board.getNumCols();
            b.setText(board.getTile(row, col).getDisplayNumber());
            nextPos++;
        }
        saveToFile(SlidingTileStartingActivity.SAVE_FILENAME);
    }

    /*
     * Gives score.
     * @Returns the score.
     */
    public int getScore(){
        return boardManager.getScore();
    }

    /*
     * Updates the score text to display.
     */
    private void updateScoreText(){
        TextView score = findViewById(R.id.Score);
        String textToSetTo = "Score: " + Integer.toString(boardManager.getScore());
        score.setText(textToSetTo);
    }

    /*
     * Updates the text to display the number of undos left.
     */
    private void updateUndosLeftText(){
        TextView undosLeft = findViewById(R.id.UndosLeft);
        String textToSetTo = "Undos Left: " + Integer.toString(boardManager.getUndosLeft());
        undosLeft.setText(textToSetTo);
    }

    /**
     * Dispatch onPause() to fragments.
     */
    @Override
    protected void onPause() {
        super.onPause();
        saveToFile(SlidingTileStartingActivity.TEMP_SAVE_FILENAME);
    }

    private void addUndoMoveButtonListener(){
        Button loadButton = findViewById(R.id.UndoButton);
        loadButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (boardManager.canUndo()){
                    boardManager.undoMove();
                    updateUndosLeftText();
                }
            }
        });
    }

    /**
     * Load the board manager from fileName.
     *
     * @param fileName the name of the file
     */
    public void loadFromFile(String fileName) {
        try {
            InputStream inputStream = this.openFileInput(fileName);
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                boardManager = (SlidingTileBoardManager) input.readObject();
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

    private void addSave1ButtonListener() {
        Button Save1Button = findViewById(R.id.SaveButton);
        Save1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToFile(SAVE_FILE_1);

            }
        });
    }

    @Override
    public void update(Observable o, Object arg) {
        display();
        if (boardManager.puzzleSolved()){
            int score = boardManager.getScore();
            Intent tmp = new Intent(SlidingTileActivity.this, SlidingTileEndActivity.class);
            tmp.putExtra("SCORE", score);
            startActivity(tmp);
        }
    }
}