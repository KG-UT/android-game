package fall2018.csc2017.gamecentre.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import fall2018.csc2017.gamecentre.R;
import fall2018.csc2017.gamecentre.games.matchingCards.MatchingCardsStartingActivity;
import fall2018.csc2017.gamecentre.games.slidingTile.SlidingTileStartingActivity;
import fall2018.csc2017.gamecentre.games.ticTacToe.TicTacToeStartingActivity;


/**
 * The game activity.
 */
public class GameChoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_choice);

        addSlidingTilesButtonListener();
        addTicTacToeButtonListener();
        addMatchingCardsButtonListener();
    }

    /**
     * Add a button listener for slidingTilesButton, the sliding tiles game option
     */
    private void addSlidingTilesButtonListener() {
        Button slidingTilesButton = findViewById(R.id.slidingTilesButton);
        slidingTilesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tmp = new Intent(GameChoiceActivity.this, SlidingTileStartingActivity.class);
                startActivity(tmp);
            }
        });
    }

    /**
     * Add a button listener for ticTacToeButton, the tic tac toe game option
     */
    private void addTicTacToeButtonListener() {
        Button slidingTilesbutton = findViewById(R.id.ticTacToeButton);
        slidingTilesbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tmp = new Intent(GameChoiceActivity.this, TicTacToeStartingActivity.class);

                startActivity(tmp);
            }
        });
    }

    /**
     * Add a button listener for MatchingCardsbutton, the Matching Cards game option
     */
    private void addMatchingCardsButtonListener(){
        Button matchingCardsButton = findViewById(R.id.MatchingCardsbutton);
        matchingCardsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tmp = new Intent(GameChoiceActivity.this, MatchingCardsStartingActivity.class);
                startActivity(tmp);
            }
        });
    }
}

