package fall2018.csc2017.gamecentre.games.matchingCards;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import fall2018.csc2017.gamecentre.CustomAdapter;
import fall2018.csc2017.gamecentre.GameActivity;
import fall2018.csc2017.gamecentre.GestureDetectGridView;
import fall2018.csc2017.gamecentre.R;
import fall2018.csc2017.gamecentre.game.Board;
import fall2018.csc2017.gamecentre.games.matchingCards.MatchingCardsBoard;
import fall2018.csc2017.gamecentre.games.slidingTile.SlidingTileStartingActivity;


public class MatchingCardsActivity extends GameActivity {

    /**
     * The board manager.
     */
    private MatchingCardsBoardManager boardManager;

    /**
     * The buttons to display.
     */
    private ArrayList<Button> tileButtons;

    public static final String SAVE_FILE_1 = "Matching_Cards_save_file.ser";

    // Grid View and calculated column height and width based on device size
    private GestureDetectGridView gridView;
    private static int columnWidth, columnHeight;

    public void display() {
        updateTileButtons();
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
                tmp.setText(board.getCard(row, col).getNumber());
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
        MatchingCardsBoard board = boardManager.getBoard();
        int nextPos = 0;
        for (Button b : tileButtons) {
            int row = nextPos / Board.getNumRows();
            int col = nextPos % Board.getNumCols();
            b.setText(board.getCard(row, col).getNumber());
            nextPos++;
        }
        saveToFile(MatchingCardsStartingActivity.SAVE_FILENAME);
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

    private void addSave1ButtonListener() {
        Button Save1Button = findViewById(R.id.MatchingSaveButton);
        Save1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToFile(SAVE_FILE_1);

            }
        });
    }
}
