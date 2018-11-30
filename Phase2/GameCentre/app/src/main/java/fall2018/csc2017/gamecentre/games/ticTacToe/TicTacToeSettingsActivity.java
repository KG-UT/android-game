package fall2018.csc2017.gamecentre.games.ticTacToe;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

import fall2018.csc2017.gamecentre.R;
import fall2018.csc2017.gamecentre.abstractClasses.SettingsActivity;

/**
 * The settings activity for Sliding Tiles (view)
 */
public class TicTacToeSettingsActivity extends SettingsActivity {
    /**
     * The desired number of rows and columns
     */
    private int NUM_ROWS = 3;
    private int NUM_COLS = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

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

    public void onRadioButtonClicked(View view) {
        switch(view.getId()) {
            case R.id.ticTacToe3By3:
                NUM_COLS = 3;
                NUM_ROWS = 3;
                break;
            case R.id.ticTacToe5By5:
                NUM_COLS = 5;
                NUM_ROWS = 5;
                break;
        }
    }
}
