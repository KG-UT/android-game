package fall2018.csc2017.gamecentre.games.matchingCards;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import fall2018.csc2017.gamecentre.DBTools;
import fall2018.csc2017.gamecentre.R;
import fall2018.csc2017.gamecentre.ScoreMatchingCards;
import fall2018.csc2017.gamecentre.User;
import fall2018.csc2017.gamecentre.abstractClasses.GameEndActivity;
import fall2018.csc2017.gamecentre.ScoreMatchingCards;
import fall2018.csc2017.gamecentre.ScoreGo;
import fall2018.csc2017.gamecentre.ScoreSlidingTiles;
import fall2018.csc2017.gamecentre.games.slidingTile.SlidingTileEndActivity;
import fall2018.csc2017.gamecentre.games.slidingTile.SlidingTileStartingActivity;

import static fall2018.csc2017.gamecentre.view.LoginActivity.currentUser;

public class MatchingCardsEndActivity extends GameEndActivity {

    /**
     * The score attained by the user.
     */
    int endScore;

    /**
     * The Database.
     */
    DBTools database = new DBTools(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.endScore = getIntent().getIntExtra("SCORE", 0);
        setContentView(R.layout.activity_matching_ending);

        saveScore();
        displayScore();
        addMenuButtonListener();
    }

    /**
     * Save score to database.
     */
    public void saveScore(){
        // Noted for later:
        // TODO: CHANGE THIS ID TO WHATEVER SHOULD BE THE RIGHT ID
        // TODO: SOMEONE MAKE THIS AND SCOREBOARD WORK.
        // TODO: TEMP
        User myUser = new User(currentUser.getUid(), currentUser.getEmail());
        ScoreMatchingCards theScore = new ScoreMatchingCards(this.endScore, myUser);

    }

    /**
     * Display the score as a TextView.
     */
    public void displayScore(){
        TextView matchingCardsText = findViewById(R.id.MatchingTilesEndScore);
        String matchingTilesEndScoreText = "Your Score: " + this.endScore;
        matchingCardsText.setText(matchingTilesEndScoreText);
    }

    /**
     * Adds a main menu button listener. (Interact on click)
     */
    public void addMenuButtonListener(){
        Button matchingCardsStartingMenuButton = findViewById(R.id.MatchingTilesBackToMainMenuButton);
        matchingCardsStartingMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toMenu();
            }
        });
    }

    /**
     * Send us to the matching cards starting menu
     */
    public void toMenu(){
        Intent tmp = new Intent(this, MatchingCardsStartingActivity.class);
        startActivity(tmp);
    }
}
