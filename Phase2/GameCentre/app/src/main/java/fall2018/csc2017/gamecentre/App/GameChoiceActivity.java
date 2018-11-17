package fall2018.csc2017.gamecentre.App;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import fall2018.csc2017.gamecentre.R;
import fall2018.csc2017.gamecentre.SlidingTile.SlidingTileStartingActivity;

/**
 * The game activity.
 */
public class GameChoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_choice);

        addSlidingTilesButtonListener();

    }
    private void addSlidingTilesButtonListener() {
        Button slidingTilesbutton = findViewById(R.id.slidingTilesButton);
        slidingTilesbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tmp = new Intent(GameChoiceActivity.this, SlidingTileStartingActivity.class);

                startActivity(tmp);
            }
        });
    }

}

