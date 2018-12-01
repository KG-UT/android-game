package fall2018.csc2017.gamecentre.scoreboardAndScores;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import fall2018.csc2017.gamecentre.R;
import fall2018.csc2017.gamecentre.database.Database;
import fall2018.csc2017.gamecentre.database.ScoreDatabaseTools;
import fall2018.csc2017.gamecentre.scoreboardAndScores.scores.ScoreMatchingCards;
import fall2018.csc2017.gamecentre.scoreboardAndScores.scores.ScoreSlidingTiles;
import fall2018.csc2017.gamecentre.scoreboardAndScores.scores.ScoreTicTacToe;
import fall2018.csc2017.gamecentre.abstractClasses.Scoreboard;
import fall2018.csc2017.gamecentre.scoreboardAndScores.scoreboards.ScoreboardMatchingCards;
import fall2018.csc2017.gamecentre.scoreboardAndScores.scoreboards.ScoreboardSlidingTies;
import fall2018.csc2017.gamecentre.scoreboardAndScores.scoreboards.ScoreboardTicTacToe;
import fall2018.csc2017.gamecentre.User;
import fall2018.csc2017.gamecentre.abstractClasses.ScoreboardActivity;
import fall2018.csc2017.gamecentre.view.LoginActivity;

import static fall2018.csc2017.gamecentre.view.LoginActivity.currentUser;

//Adapted from https://stacktips.com/tutorials/android/android-listview-tutorial

/**
 * The general sliding tile scoreboard activity class
 */
public class ScoreboardGameUserActivity extends ScoreboardActivity {

    // TODO: Rework this, this is temp.
    private User myUser = new User(currentUser.getUid(), currentUser.getEmail());

    private ScoreDatabaseTools data = new ScoreDatabaseTools();

    public FirebaseFirestore dataBaseInstance = data.db;

    private ArrayList<String> stringScoreboard = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard_display);

        scoreBoardView();
        scoreBoardTitleView();

        addOtherScoreboardButtonListener();
    }

    /**
     * Add other scoreboard button listener.
     */
    protected void addOtherScoreboardButtonListener() {
        Button newButton = findViewById(R.id.scoreboardToUserScoreboardButton);
        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToOtherScoreboard();
            }
        });
    }

    /**
     * Changes the state of the scoreboard between the game scoreboard and the user scoreboard.
     */
     private void switchToOtherScoreboard() {
         isGameScoreboard = !isGameScoreboard;
         scoreBoardTitleView();
         scoreBoardView();
         stringScoreboard = new ArrayList<>();
    }

    /**
     * Displays the scoreboard.
     */
    protected void scoreBoardView() {
        ListView ScoreboardListView;
        setupCombinedScoreboard();

        ArrayAdapter arrayAdapter;

        ScoreboardListView = findViewById(R.id.scoreboardListViewer);

        arrayAdapter = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, stringScoreboard);
        ScoreboardListView.setAdapter(arrayAdapter);
    }

    /**
     * Displays the scoreboard title.
     */
    protected void scoreBoardTitleView() {
        TextView scoreBoardTitle = findViewById(R.id.scoreboardTitleTextView);
        if (isGameScoreboard) {
            String scoreBoardTitleText = "General Scoreboard";
            scoreBoardTitle.setText(scoreBoardTitleText);
        } else {
            String scoreBoardTitleText = "Personal Scoreboard";
            scoreBoardTitle.setText(scoreBoardTitleText);
        }
    }

    /**
     * Sets up the combined scoreboard for all games.
     *
     * @return the scoreboard in a list of strings form
     */
    private void setupCombinedScoreboard() {
        getTicTacToeScoresFromDatabase();
        getSlidingTilesScoresFromDatabase();
        getMatchingCardsScoresFromDatabase();
    }

    /**
     * Sets up the the scoreboard for a game.
     *
     * @return the scoreboard of the game
     */
    private Scoreboard setupSlidingTilesScoreboard(List<ScoreSlidingTiles> listOfScores, String nameOfGame) {
        ScoreboardSlidingTies newScoreboard = new ScoreboardSlidingTies(listOfScores, nameOfGame);
        newScoreboard.organizeScoreBoard();
        return newScoreboard;
    }

    /**
     * Sets up the the scoreboard for a game.
     *
     * @return the scoreboard of the game
     */
    private Scoreboard setupTicTacToeScoreboard(List<ScoreTicTacToe> listOfScores, String nameOfGame) {
        ScoreboardTicTacToe newScoreboard = new ScoreboardTicTacToe(listOfScores, nameOfGame);
        newScoreboard.organizeScoreBoard();
        return newScoreboard;
    }

    /**
     * Sets up the the scoreboard for a game.
     *
     * @return the scoreboard of the game
     */
    private Scoreboard setupMatchingCardsScoreboard(List<ScoreMatchingCards> listOfScores, String nameOfGame) {
        ScoreboardMatchingCards newScoreboard = new ScoreboardMatchingCards(listOfScores, nameOfGame);
        newScoreboard.organizeScoreBoard();
        return newScoreboard;
    }

    /**
     * Gets all the Sliding Tile scores from the database.
     *
     * @return the list of all sliding tile scores.
     */
    public void getSlidingTilesScoresFromDatabase() {
        if (isGameScoreboard) {
            data.getUserSlidingTileScores(FirebaseAuth.getInstance().getCurrentUser())
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                if(document.exists()) {
                                    ArrayList<String> rawScores = (ArrayList<String>) document.get("scores");
                                    ArrayList<ScoreSlidingTiles> scores = new ArrayList<>();
                                    for(int i = 0; i < rawScores.size(); i++) {
                                        scores.add(new ScoreSlidingTiles(Integer.parseInt(rawScores.get(i)), LoginActivity.currentUser.getEmail()));
                                    }
                                    stringScoreboard.addAll(setupSlidingTilesScoreboard(scores, "Sliding Tiles").getScoreBoardDataStringForm());
                                }
                            }
                        }
                    });
        }
    }

    /**
     * Gets all the TicTacToe scores from the database.
     *
     * @return the list of all TicTacToe scores.
     */
    public void getTicTacToeScoresFromDatabase() {
        if (isGameScoreboard) {
            data.getUserTicTacToeScores(FirebaseAuth.getInstance().getCurrentUser())
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if(document.exists()) {
                                ArrayList<String> rawScores = (ArrayList<String>) document.get("scores");
                                ArrayList<ScoreTicTacToe> scores = new ArrayList<>();
                                for(int i = 0; i < rawScores.size(); i++) {
                                    scores.add(new ScoreTicTacToe(Integer.parseInt(rawScores.get(i)), LoginActivity.currentUser.getEmail()));
                                }
                                stringScoreboard.addAll(setupTicTacToeScoreboard(scores, "TicTacToe").getScoreBoardDataStringForm());
                            }
                        }
                    }
                });
        }
    }

    /**
     * Gets all the Matching Cards scores from the database.
     *
     * @return the list of all Go scores.
     */
    public void getMatchingCardsScoresFromDatabase() {
        if (isGameScoreboard) {
            data.getUserMatchingCardScores(FirebaseAuth.getInstance().getCurrentUser())
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                if(document.exists()) {
                                    ArrayList<String> rawScores = (ArrayList<String>) document.get("scores");
                                    ArrayList<ScoreMatchingCards> scores = new ArrayList<>();
                                    for(int i = 0; i < rawScores.size(); i++) {
                                        scores.add(new ScoreMatchingCards(Integer.parseInt(rawScores.get(i)), LoginActivity.currentUser.getEmail()));
                                    }
                                    stringScoreboard.addAll(setupMatchingCardsScoreboard(scores, "MatchingCards").getScoreBoardDataStringForm());
                                }
                            }
                        }
                    });
        }
    }
}