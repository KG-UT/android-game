package fall2018.csc2017.gamecentre.database;

import android.util.Log;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import fall2018.csc2017.gamecentre.scoreboardAndScores.scores.ScoreMatchingCards;
import fall2018.csc2017.gamecentre.scoreboardAndScores.scores.ScoreSlidingTiles;
import fall2018.csc2017.gamecentre.scoreboardAndScores.scores.ScoreTicTacToe;

public class ScoreDatabaseTools {
    /* *******************
     * Score database values are:
     *          st-scores
     *          mc-scores
     *          ttt-scores
     *********************/

    /**
     * The database.
     */
    private FirebaseFirestore db = Database.getDatabase();

    /**
     * A list of user scores
     *
     * Future functionality
     */
    private ArrayList<ScoreTicTacToe> userScores = new ArrayList<>();

    /**
     * Inserts a user's score into the Tic Tac Toe Score database.
     *
     * @param userScore The user's score.
     */
    public void saveToDatabase(ScoreTicTacToe userScore, String scoreType) {
        String owner = userScore.getOwner();
        // We store the scores as strings to prevent nulls while unboxing.
        String strScore = String.valueOf(userScore.getUserScore());
        saveToDatabaseHelper(owner, strScore, scoreType);
    }

    /**
     * Inserts a user's score into the Sliding Tile Score database.
     *
     * @param userScore The user's score.
     */
    public void saveToDatabase(ScoreSlidingTiles userScore, String scoreType) {
        String owner = userScore.getOwner();
        // We store the scores as strings to prevent nulls while unboxing.
        String strScore = String.valueOf(userScore.getUserScore());
        saveToDatabaseHelper(owner, strScore, scoreType);
    }

    /**
     * Inserts a user's score into the Matching Card Score database.
     *
     * @param userScore The user's score.
     */
    public void saveToDatabase(ScoreMatchingCards userScore, String scoreType) {
        String owner = userScore.getOwner();
        // We store the scores as strings to prevent nulls while unboxing.
        String strScore = String.valueOf(userScore.getUserScore());
        saveToDatabaseHelper(owner, strScore, scoreType);
    }

    /**
     * The helps save scores to database.
     */
    private void saveToDatabaseHelper(String owner, String strScore, String scoreType){
        DocumentReference docRef = db.collection(scoreType).document(owner);
        // Adds document data with id of "owner" and the score.
        Map<String, Object> data = new HashMap<>();
        data.put("owner", owner);
        // Async writing of data
        try {
            docRef.set(data, SetOptions.merge());
            docRef.update("scores", FieldValue.arrayUnion(strScore));
        } catch (Exception e) {
            Log.e("TEMP", "Error inserting user's score into database.");
        }
    }

    /**
     * Returns all the scores for the specified game associated with a specific user.
     *
     * @param user The current user
     * @return All the user's scores for a specific game.
     */
    public Task<DocumentSnapshot> getUserTicTacToeScores(FirebaseUser user) {
        userScores = new ArrayList<>();
        String owner = user.getUid();
        return db.collection("ttt-scores").document(owner).get();
    }

    /**
     * Returns all the scores for the specified game associated with a specific user.
     *
     * @param user The current user
     * @return All the user's scores for a specific game.
     */
    public Task<DocumentSnapshot> getUserSlidingTileScores(FirebaseUser user) {
        userScores = new ArrayList<>();
        String owner = user.getUid();
        return db.collection("st-scores").document(owner).get();
    }

    /**
     * Returns all the scores for matching cards associated with a specific user.
     *
     * @param user The current user
     * @return All the user's scores for matching cards.
     */
    public Task<DocumentSnapshot> getUserMatchingCardScores(FirebaseUser user) {
        userScores = new ArrayList<>();
        String owner = user.getUid();
        return db.collection("mc-scores").document(owner).get();
    }
}
