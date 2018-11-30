package fall2018.csc2017.gamecentre.games.matchingCards;

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
import java.util.Observable;

import fall2018.csc2017.gamecentre.CustomAdapter;
import fall2018.csc2017.gamecentre.GestureDetectGridView;
import fall2018.csc2017.gamecentre.R;
import fall2018.csc2017.gamecentre.abstractClasses.Board;
import fall2018.csc2017.gamecentre.abstractClasses.BoardManager;
import fall2018.csc2017.gamecentre.abstractClasses.GameActivity;

/**
 * The MatchingCardsActivity class which displays the Matching Card game.
 */
public class MatchingCardsActivity extends GameActivity {

    /**
     * The board manager.
     */
    private MatchingCardsBoardManager boardManager;

    /**
     * The buttons to display.
     */
    private ArrayList<Button> tileButtons;

    /**
     * The file path of the corresponding save file.
     */
    public static final String SAVE_FILE = "Matching_Cards_save_file.ser";

    // Grid View and calculated column height and width based on device size
    /**
     * The corresponding GestureDetectGridView associated with this activity.
     */
    private GestureDetectGridView gridView;

    /**
     * The int value of the width and height of the board.
     */
    private static int columnWidth, columnHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(boardManager == null)
        { boardManager = new MatchingCardsBoardManager(4,4); }

        createTileButtons(this);
        setContentView(R.layout.activity_matching_main);
        // Add View to activity
        gridView = findViewById(R.id.matchingGrid);
        gridView.setNumColumns(Board.getNumCols());
        gridView.setBoardManager(boardManager);
        // TODO: My branch doesn't have addObserver.
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
        addSaveButtonListener();
    }

    /**
     * Displays the parts of the matching cards game.
     */
    public void display() {
        updateTileButtons();
        updateScoreText();
        gridView.setAdapter(new CustomAdapter(tileButtons, columnWidth, columnHeight));

    }

    /**
     * Create the buttons for displaying the tiles.
     *
     * @param context the context
     */
    private void createTileButtons(Context context) {
        MatchingCardsBoard board = boardManager.getBoard();
        tileButtons = new ArrayList<>();
        for (int row = 0; row != Board.getNumRows(); row++) {
            for (int col = 0; col != Board.getNumCols(); col++) {
                Button tmp = new Button(context);
                tmp.setText(Integer.toString(board.getCard(row, col).getNumber()));
                tmp.setTextSize(64);
                tmp.setBackgroundColor(Color.parseColor("#ffffff"));
                this.tileButtons.add(tmp);
            }
        }
    }

    /**
     * Update the backgrounds on the buttons to match the tiles.
     */
    public void updateTileButtons() {
        MatchingCardsBoard board = boardManager.getBoard();
        int nextPos = 0;
        for (Button b : tileButtons) {
            int row = nextPos / Board.getNumRows();
            int col = nextPos % Board.getNumCols();
            MatchingCardsTile card = board.getCard(row, col);
            if (card.isFaceUp()){
                b.setText(Integer.toString(card.getNumber()));
            } else {b.setText("");}
            nextPos++;
        }
        saveToFile(MatchingCardsStartingActivity.SAVE_FILENAME);
    }

    /**
     * Dispatch onPause() to fragments.
     */
    @Override
    protected void onPause() {
        super.onPause();
        saveToFile(MatchingCardsStartingActivity.TEMP_SAVE_FILENAME_1);
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
     * Adds a listener to the MatchingSaveButton and determines when it has been pressed.
     */
    private void addSaveButtonListener() {
        Button SaveButton = findViewById(R.id.MatchingSaveButton);
        SaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToFile(SAVE_FILE);

            }
        });
    }

    /**
     * Updates the score text to display.
     */
    private void updateScoreText(){
        TextView score = findViewById(R.id.MatchingScoreText);
        String textToSetTo = "Score: " + Integer.toString(boardManager.getScore());
        score.setText(textToSetTo);
    }

    @Override
    public void update(Observable o, Object arg) {
        display();
        if (boardManager.puzzleSolved()){
            int score = boardManager.getScore();
            Intent tmp = new Intent(MatchingCardsActivity.this, MatchingCardsEndActivity.class);
            tmp.putExtra("SCORE", score);
            startActivity(tmp);
        }
    }
}
