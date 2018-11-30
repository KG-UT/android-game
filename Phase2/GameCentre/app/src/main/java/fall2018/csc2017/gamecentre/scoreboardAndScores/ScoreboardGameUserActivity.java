package fall2018.csc2017.gamecentre.scoreboardAndScores;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fall2018.csc2017.gamecentre.R;
import fall2018.csc2017.gamecentre.scoreboardAndScores.scores.ScoreMatchingCards;
import fall2018.csc2017.gamecentre.scoreboardAndScores.scores.ScoreSlidingTiles;
import fall2018.csc2017.gamecentre.scoreboardAndScores.scores.ScoreTicTacToe;
import fall2018.csc2017.gamecentre.abstractClasses.Scoreboard;
import fall2018.csc2017.gamecentre.scoreboardAndScores.scoreboards.ScoreboardMatchingCards;
import fall2018.csc2017.gamecentre.scoreboardAndScores.scoreboards.ScoreboardSlidingTies;
import fall2018.csc2017.gamecentre.scoreboardAndScores.scoreboards.ScoreboardTicTacToe;
import fall2018.csc2017.gamecentre.User;
import fall2018.csc2017.gamecentre.abstractClasses.ScoreboardActivity;

import static fall2018.csc2017.gamecentre.view.LoginActivity.currentUser;

//Adapted from https://stacktips.com/tutorials/android/android-listview-tutorial

/**
 * The general sliding tile scoreboard activity class
 */
public class ScoreboardGameUserActivity extends ScoreboardActivity {
    // TODO: Rework this, this is temp.
    private User myUser = new User(currentUser.getUid(), currentUser.getEmail());

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
    }

    /**
     * Displays the scoreboard.
     */
    protected void scoreBoardView() {
        ListView ScoreboardListView;
        ArrayAdapter arrayAdapter;

        ScoreboardListView = findViewById(R.id.scoreboardListViewer);

        arrayAdapter = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, setupCombinedScoreboard());
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
    private List<String> setupCombinedScoreboard() {
        List<String> scoreBoardListData = new ArrayList<>();

        scoreBoardListData.addAll(setupSlidingTilesScoreboard(getSlidingTilesScoresFromDatabase(),
                "Sliding Tiles").getScoreBoardDataStringForm());
        scoreBoardListData.addAll(setupSlidingTilesScoreboard(getTicTacToeScoresFromDatabase(),
                "TicTacToe").getScoreBoardDataStringForm());
        scoreBoardListData.addAll(setupSlidingTilesScoreboard(getMatchingCardsScoresFromDatabase(),
                "Matching Cards").getScoreBoardDataStringForm());

        return scoreBoardListData;
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
    private List<ScoreSlidingTiles> getSlidingTilesScoresFromDatabase() {
        if (isGameScoreboard) {
//            List<User> listOfUsers = databaseTools.getAllUsers();
            List<ScoreSlidingTiles> listOfScoresSlidingTiles = new ArrayList<>();
            // TODO: This is deprecated. Need to implement method to get all users.
//            for (User user : listOfUsers) {
//                listOfScoresSlidingTiles.addAll(databaseTools.getUserSlidingTileScores(user.getUsername()));
//            }
            return listOfScoresSlidingTiles;
        } else {
            return databaseTools.getUserSlidingTileScores(myUser.getUsername());
        }
    }

    /**
     * Gets all the TicTacToe scores from the database.
     *
     * @return the list of all TicTacToe scores.
     */
    private List<ScoreSlidingTiles> getTicTacToeScoresFromDatabase() {
        if (isGameScoreboard) {
//            List<User> listOfUsers = databaseTools.getAllUsers();
            // TODO: Deprecated. WIll implement new method.
            List<ScoreSlidingTiles> listOfScoresSlidingTiles = new ArrayList<>();
//            for (User user : listOfUsers) {
//                listOfScoresSlidingTiles.addAll(databaseTools.getUserSlidingTileScores(user.getUsername()));
//            }
            return listOfScoresSlidingTiles;
        } else {
            return databaseTools.getUserSlidingTileScores(myUser.getUsername());
        }
    }

    /**
     * Gets all the Matching Cards scores from the database.
     *
     * @return the list of all Go scores.
     */
    private List<ScoreSlidingTiles> getMatchingCardsScoresFromDatabase() {
        if (isGameScoreboard) {
//            List<User> listOfUsers = databaseTools.getAllUsers();
            // TODO: Deprecated. Will fix.
            List<ScoreSlidingTiles> listOfScoresSlidingTiles = new ArrayList<>();
//            for (User user : listOfUsers) {
//                listOfScoresSlidingTiles.addAll(databaseTools.getUserSlidingTileScores(user.getUsername()));
//            }
            return listOfScoresSlidingTiles;
        } else {
            return databaseTools.getUserSlidingTileScores(myUser.getUsername());
        }
    }
}