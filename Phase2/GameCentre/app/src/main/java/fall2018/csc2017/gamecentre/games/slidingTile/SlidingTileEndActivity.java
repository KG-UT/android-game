package fall2018.csc2017.gamecentre.games.slidingTile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import fall2018.csc2017.gamecentre.abstractClasses.GameEndActivity;
import fall2018.csc2017.gamecentre.R;
import fall2018.csc2017.gamecentre.ScoreSlidingTiles;


/**
 * The class responsible for handling the end-of-game behaviour for Sliding Tile games.
 */
public class SlidingTileEndActivity extends GameEndActivity {
    /**
     * The score attained by the user.
     */
    private int endScore;

    /**
     * The Database.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.endScore = getIntent().getIntExtra("SCORE", 0);
        setContentView(R.layout.activity_ending_);

        saveScore();
        displayScore();
        addMenuButtonListener();
    }

    @Override
    public void displayScore() {
        TextView slidingTilesText = findViewById(R.id.EndScore);
        String slidingTilesEndMessage = "Score: " + this.endScore;
        slidingTilesText.setText(slidingTilesEndMessage);
    }

    @Override
    public void addMenuButtonListener() {
        Button slidingTilesMenuButton = findViewById(R.id.Menu);
        slidingTilesMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toMenu();
            }
        });
    }

    @Override
    public void toMenu(){
        Intent tmp = new Intent(this, SlidingTileStartingActivity.class);
        startActivity(tmp);
    }

    @Override
    public void saveScore() {
        // Noted for later:
        // TODO: CHANGE THIS ID TO WHATEVER SHOULD BE THE RIGHT ID
        // TODO: SOMEONE MAKE THIS AND SCOREBOARD WORK.

        ScoreSlidingTiles theScore = new ScoreSlidingTiles(this.endScore, "FUCK");

    }
}
