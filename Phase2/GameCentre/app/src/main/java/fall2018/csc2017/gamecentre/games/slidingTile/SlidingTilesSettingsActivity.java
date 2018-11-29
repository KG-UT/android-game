package fall2018.csc2017.gamecentre.games.slidingTile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.ToggleButton;

import fall2018.csc2017.gamecentre.R;
import fall2018.csc2017.gamecentre.abstractClasses.SettingsActivity;
import fall2018.csc2017.gamecentre.boardManagers.SlidingTileBoardManager;
import fall2018.csc2017.gamecentre.firebase.SlidingTileDatabaseTools;

/**
 * The settings activity for Sliding Tiles (view)
 */
public class SlidingTilesSettingsActivity extends SettingsActivity {
    /**
     * The desired number of rows and columns
     */
    public int NUM_ROWS = 4;
    public int NUM_COLS = 4;

    private SlidingTileDatabaseTools slidingTileDatabaseTools = new SlidingTileDatabaseTools();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addChallengeButtonListeners();
    }

    @Override
    public int getSettingsActivityId() {
        return R.layout.activity_sliding_tile_settings;
    }

    @Override
    public void switchToGame() {
        this.addSetting("NUM_ROWS", NUM_ROWS);
        this.addSetting("NUM_COLS", NUM_COLS);

        Intent tmp = new Intent(this, SlidingTileActivity.class);
        tmp.putExtra("SETTINGS", this.getSettings());
        startActivity(tmp);
    }

    /**
     * Set the listeners to changes in the selected challenge levels by the user
     */
    public void addChallengeButtonListeners() {
        // Code adapted from:
        // https://stackoverflow.com/questions/2379527/android-how-to-get-a-radiogroup-with-togglebuttons
        ((RadioGroup) findViewById(R.id.ChallengeLevels)).setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int id) {
                        for (int j = 0; j < radioGroup.getChildCount(); j++) {
                            ToggleButton btn = (ToggleButton) radioGroup.getChildAt(j);
                            btn.setChecked(btn.getId() == id);
                        }
                    }
                }
        );
    }

    /**
     * Handle a game challenge level selection
     */
    public void setChallenge(View view) {
        int id = view.getId();
        ((RadioGroup)view.getParent()).check(view.getId());
        switch(id) {
            case R.id.ThreeByThree:
                NUM_COLS = 3;
                NUM_ROWS = 3;
                break;
            case R.id.FourByFour:
                NUM_COLS = 4;
                NUM_ROWS = 4;
                break;
            case R.id.FiveByFive:
                NUM_COLS = 5;
                NUM_ROWS = 5;
                break;
        }
    }
}
