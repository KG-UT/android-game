package fall2018.csc2017.gamecentre.database;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.core.Tag;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fall2018.csc2017.gamecentre.User;
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
    public FirebaseFirestore db = Database.getDatabase();

    /**
     * A list of user scores
     */
    public ArrayList<ScoreTicTacToe> userScores = new ArrayList<>();

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

    /**
     * Returns all the scores for all users for tic tac toe.
     *
     * @return all the scores for tic tac toe ever stored.
     */
//    public ArrayList<ScoreTicTacToe> getAllTicTacToeGameScores() {
//        ApiFuture<QuerySnapshot> query = db.collection("ttt-scores").get();
//        ArrayList<ScoreTicTacToe> allTTTScores = new ArrayList<>();
//
//        try {
//            QuerySnapshot querySnapshot = query.get();
//            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
//
//            for (QueryDocumentSnapshot document : documents) {
//                String tmpOwner = document.getString("owner");
//                int tmpScore = Integer.valueOf(document.getString("score"));
//                ScoreTicTacToe tmp = new ScoreTicTacToe(tmpScore, tmpOwner);
//                allTTTScores.add(tmp);
//            }
//            return allTTTScores;
//        } catch (Exception e) {
//            Log.e("TAG", "Error getting all game scores.");
//        }
//        // TODO: Less cancer
//        return null;
//    }

    /**
     * Returns all the scores for all users for sliding tile.
     *
     * @return all the scores for the specific game ever stored.
     */
//    public ArrayList<ScoreSlidingTiles> getAllSlidingTileGameScores() {
//        ApiFuture<QuerySnapshot> query = db.collection("st-scores").get();
//        ArrayList<ScoreSlidingTiles> allSTScores = new ArrayList<>();
//
//        try {
//            QuerySnapshot querySnapshot = query.get();
//            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
//
//            for (QueryDocumentSnapshot document : documents) {
//                String tmpOwner = document.getString("owner");
//                int tmpScore = Integer.valueOf(document.getString("score"));
//                ScoreSlidingTiles tmp = new ScoreSlidingTiles(tmpScore, tmpOwner);
//                allSTScores.add(tmp);
//            }
//            return allSTScores;
//        } catch (Exception e) {
//            Log.e("TAG", "Error getting all game scores.");
//        }
//        // TODO: Less cancer
//        return null;
//    }

    /**
     * Returns all the scores for all users for a specific game.
     *
     * @return all the scores for the specific gameever stored.
     */
//    public ArrayList<ScoreMatchingCards> getAllMatchingCardsGameScores() {
//        ApiFuture<QuerySnapshot> query = db.collection("mc-scores").get();
//        ArrayList<ScoreMatchingCards> allMCScores = new ArrayList<>();
//
//        try {
//            QuerySnapshot querySnapshot = query.get();
//            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
//
//            for (QueryDocumentSnapshot document : documents) {
//                String tmpOwner = document.getString("owner");
//                int tmpScore = Integer.valueOf(document.getString("score"));
//                ScoreMatchingCards tmp = new ScoreMatchingCards(tmpScore, tmpOwner);
//                allMCScores.add(tmp);
//            }
//            return allMCScores;
//        } catch (Exception e) {
//            Log.e("TAG", "Error getting all game scores.");
//        }
//        // TODO: Less cancer
//        return null;
//    }
}
