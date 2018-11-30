package fall2018.csc2017.gamecentre.database;

import android.util.Log;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fall2018.csc2017.gamecentre.ScoreAbstract;
import fall2018.csc2017.gamecentre.ScoreTicTacToe;
import fall2018.csc2017.gamecentre.User;

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
    private Firestore db = Database.getDatabase();

    /**
     * Inserts a user's score into the specified score database.
     *
     * @param userScore The user's score.
     */
    public void saveToDatabase(ScoreAbstract userScore, String scoreType) {
        String owner = userScore.getOwner();
        // We store the scores as strings to prevent nulls while unboxing.
        String strScore = String.valueOf(userScore.getUserScore());

        DocumentReference docRef = db.collection(scoreType).document(owner);
        // Adds document data with id of "owner" and the score.
        Map<String, Object> data = new HashMap<>();
        data.put("owner", owner);
        data.put("score", strScore);
        // Async writing of data
        try {
            docRef.set(data);
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
    public ArrayList<ScoreAbstract> getUserScores(User user, String scoreType) {
        String owner = user.getUsername();
        ArrayList<ScoreAbstract> userScores = new ArrayList<>();

        ApiFuture<QuerySnapshot> query =
                db.collection(scoreType).whereEqualTo(owner, true).get();
        // TODO: is this an issue??
        List<QueryDocumentSnapshot> documents = query.get().getDocuments();

        for (DocumentSnapshot document : documents) {
            String tmpOwner = document.getString("owner");
            int tmpScore = Integer.valueOf(document.getString("score"));
            ScoreAbstract tmp = new ScoreTicTacToe(tmpScore, tmpOwner);
            userScores.add(tmp);
        }
        return userScores;
    }

    /**
     * Returns all the scores for all users for a specific game.
     *
     * @return all the scores for the specific gameever stored.
     */
    public ArrayList<ScoreAbstract> getAllGameScores(String scoreType) {
        ApiFuture<QuerySnapshot> query = db.collection(scoreType).get();
        ArrayList<ScoreAbstract> allTTTScores = new ArrayList<>();
        // TODO: Will we lose marks? :-(
        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();

        for (QueryDocumentSnapshot document : documents) {
            String tmpOwner = document.getString("owner");
            int tmpScore = Integer.valueOf(document.getString("score"));
            ScoreAbstract tmp = new ScoreTicTacToe(tmpScore, tmpOwner);
            allTTTScores.add(tmp);
        }
        return allTTTScores;
    }
}
