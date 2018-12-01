package fall2018.csc2017.gamecentre.games.matchingCards;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import fall2018.csc2017.gamecentre.R;
import fall2018.csc2017.gamecentre.scoreboardAndScores.scores.ScoreMatchingCards;
import fall2018.csc2017.gamecentre.abstractClasses.GameEndActivity;


/**
 * The MatchingCardsEndActivity class which displays the screen after the game is finished.
 */
public class MatchingCardsEndActivity extends GameEndActivity {

    /**
     * The score attained by the user.
     */
    private int endScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.endScore = getIntent().getIntExtra("SCORE", 0);
        setContentView(R.layout.activity_ending_);

        saveScore();
        displayScore();
        addMenuButtonListener();
    }

    /**
     * Save score to database.
     */
    public void saveScore(){
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        ScoreMatchingCards theScore = new ScoreMatchingCards(this.endScore, currentUser.getUid());
        databaseTool.saveToDatabase(theScore, "mc-scores");
    }

    /**
     * Display the score as a TextView.
     */
    public void displayScore(){
        TextView matchingCardsText = findViewById(R.id.EndScore);
        String matchingCardsEndMessage = "Your Score: " + this.endScore;
        matchingCardsText.setText(matchingCardsEndMessage);
    }

    /**
     * Adds a main menu button listener. (Interact on click)
     */
    public void addMenuButtonListener(){
        Button matchingCardsMenuButton = findViewById(R.id.Menu);
        matchingCardsMenuButton.setOnClickListener(new View.OnClickListener() {
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
