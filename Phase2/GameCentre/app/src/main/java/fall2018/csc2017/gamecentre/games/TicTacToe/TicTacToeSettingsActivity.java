package fall2018.csc2017.gamecentre.games.TicTacToe;

import android.content.Intent;
import android.os.Bundle;

import fall2018.csc2017.gamecentre.R;
import fall2018.csc2017.gamecentre.SettingsActivity;

/**
 * The settings activity for Sliding Tiles (view)
 */
public class TicTacToeSettingsActivity extends SettingsActivity {
    /**
     * The desired number of rows and columns
     */
    public int NUM_ROWS = 3;
    public int NUM_COLS = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getSettingsActivityId() {
        return R.layout.activity_tic_tac_toe_settings;
    }

    @Override
    public void switchToGame() {
        this.addSetting("NUM_ROWS", NUM_ROWS);
        this.addSetting("NUM_COLS", NUM_COLS);

        Intent tmp = new Intent(this, TicTacToeActivity.class);
        tmp.putExtra("SETTINGS", this.getSettings());
        startActivity(tmp);
    }
}
