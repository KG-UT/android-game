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

import static fall2018.csc2017.gamecentre.view.LoginActivity.currentUser;

public class MatchingCardsEndActivity extends GameEndActivity {

    /**
     * The score attained by the user.
     */
    int score;

    /**
     * The Database.
     */
    DBTools database = new DBTools(MatchingCardsEndActivity.this);

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
    public void saveScore() {
        // Noted for later:
        // TODO: CHANGE THIS ID TO WHATEVER SHOULD BE THE RIGHT ID
        // TODO: SOMEONE MAKE THIS AND SCOREBOARD WORK.
        // TODO: TEMP
        User myUser = new User(currentUser.getUid(), currentUser.getEmail());
        ScoreMatchingCards theScore = new ScoreMatchingCards(this.score, myUser);
       // database.insertSlidingTileScore(theScore);
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
        Intent tmp = new Intent(this, MatchingCardsStartingActivity.class);
        startActivity(tmp);
    }
}
