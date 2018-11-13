package fall2018.csc2017.gamecentre;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.HashMap;

/**
 * The abstract class for a game's Settings activity.
 */
abstract public class SettingsActivity extends AppCompatActivity {
    /**
     * Stores user selected settings
     */
    HashMap<String, Object> settings = new HashMap<>();

    /**
     * Switch to the GameActivity linked to the settings
     */
    abstract void switchToGame();

    /**
     * Return the id of the setting view to be displayed
     * @return id of the setting view to be displayed
     */
    abstract int getSettingsActivityId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        addSettingsView();
        addStartGameButtonListener();
    }

    /**
     * Add the settings view to the settings container for display
     */
    private void addSettingsView() {
        // Code adapted from:
        // https://stackoverflow.com/questions/31735548/adding-a-xml-to-a-linearlayout-item-in-android-studio
        View settingsView = getLayoutInflater().inflate(getSettingsActivityId(), null);

        LinearLayout settingsContainer = findViewById(R.id.SettingsContainer);
        settingsContainer.addView(settingsView);
    }

    /**
     * Create a handler for the start game button
     */
    private void addStartGameButtonListener() {
        Button startGameButton = findViewById(R.id.StartGameButton);
        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToGame();
            }
        });
    }

    /**
     * Add val with key to the settings
     * @param key - The key of the setting
     * @param val - The val of the setting
     */
    protected void addSetting(String key, Object val) {
        this.settings.put(key, val);
    }
}
