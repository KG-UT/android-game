package fall2018.csc2017.gamecentre.games.ticTacToe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import fall2018.csc2017.gamecentre.DBTools;
import fall2018.csc2017.gamecentre.GameEndActivity;
import fall2018.csc2017.gamecentre.R;
import fall2018.csc2017.gamecentre.ScoreSlidingTiles;
import fall2018.csc2017.gamecentre.games.slidingTile.SlidingTileStartingActivity;

import static fall2018.csc2017.gamecentre.LoginActivity.myUser;

/**
 * The class responsible for handling the end-of-game behaviour for Sliding Tile games.
 */
public class TicTacToeEndActivity extends GameEndActivity {
    /**
     * The score attained by the user.
     */
    int score;

    /**
     * The Database.
     */
    DBTools database = new DBTools(TicTacToeEndActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.score = getIntent().getIntExtra("SCORE", 0);
        setContentView(R.layout.activity_ending_);
        saveScore();
        displayScore();
        addMenuButtonListener();
    }

    @Override
    public void displayScore() {
        TextView score = findViewById(R.id.EndScore);
        String textToSetTo = "Score: " + this.score;
        score.setText(textToSetTo);
    }

    @Override
    public void addMenuButtonListener() {
        Button menu = findViewById(R.id.Menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toMenu();
            }
        });
    }

    @Override
    public void toMenu(){
        Intent tmp = new Intent(this, TicTacToeStartingActivity.class);
        startActivity(tmp);
    }

    @Override
    public void saveScore() {
        // Noted for later:
        // TODO: CHANGE THIS ID TO WHATEVER SHOULD BE THE RIGHT ID
        // TODO: SOMEONE MAKE THIS AND SCOREBOARD WORK.
        ScoreSlidingTiles theScore = new ScoreSlidingTiles(this.score, myUser);
        database.insertSlidingTileScore(theScore);
    }
}
