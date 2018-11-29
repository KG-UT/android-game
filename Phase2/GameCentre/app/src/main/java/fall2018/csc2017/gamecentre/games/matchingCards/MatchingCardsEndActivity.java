package fall2018.csc2017.gamecentre.games.matchingCards;

import fall2018.csc2017.gamecentre.DBTools;
import fall2018.csc2017.gamecentre.GameEndActivity;

public class MatchingCardsEndActivity extends GameEndActivity {

    /**
     * The score attained by the user.
     */
    int score;

    /**
     * The Database.
     */
    DBTools database = new DBTools(MatchingCardsEndActivity.this);

    /**
     * Display the score as a Textview.
     */
    public void displayScore(){};

    /**
     * Save score to database.
     */
    public void saveScore(){};

    /**
     * Adds a main menu button listener. (Interact on click)
     */
    public void addMenuButtonListener(){};

    /**
     * Send us to the main menu
     */
    public void toMenu(){};
}
