package fall2018.csc2017.gamecentre.Go;

import android.os.Bundle;

import fall2018.csc2017.gamecentre.SettingsActivity;

public class GoSettingsActivity extends SettingsActivity {
    @Override
    protected void addSetting(String key, Object val) {
        super.addSetting(key, val);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    void switchToGame() {
        int temp =0;
    }

    @Override
    public int getSettingsActivityId() {
        return 0;
    }
}
