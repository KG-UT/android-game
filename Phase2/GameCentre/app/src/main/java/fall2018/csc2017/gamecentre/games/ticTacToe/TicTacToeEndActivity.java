package fall2018.csc2017.gamecentre.games.ticTacToe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import fall2018.csc2017.gamecentre.DBTools;
import fall2018.csc2017.gamecentre.R;
import fall2018.csc2017.gamecentre.scoreboardAndScores.scores.ScoreSlidingTiles;
import fall2018.csc2017.gamecentre.User;
import fall2018.csc2017.gamecentre.abstractClasses.GameEndActivity;


import static fall2018.csc2017.gamecentre.view.LoginActivity.currentUser;

/**
 * The class responsible for handling the end-of-game behaviour for Sliding Tile games.
 */
public class TicTacToeEndActivity extends GameEndActivity {
    /**
     * The score attained by the user.
     */
    private int endScore;

    /**
     * The Database.
     */
    DBTools database = new DBTools(TicTacToeEndActivity.this);

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
        TextView ticTacToeText = findViewById(R.id.EndScore);
        String ticTacToeEndMessage = "Score: " + this.endScore;
        ticTacToeText.setText(ticTacToeEndMessage);
    }

    @Override
    public void addMenuButtonListener() {
        Button ticTacToeMenuButton = findViewById(R.id.Menu);
        ticTacToeMenuButton.setOnClickListener(new View.OnClickListener() {
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
        // TODO: THIS IS TEMP, FIX LATER
        User TEMP_USER = new User(currentUser.getUid(), currentUser.getEmail());
        ScoreSlidingTiles theScore = new ScoreSlidingTiles(this.endScore, TEMP_USER);
    }
}
