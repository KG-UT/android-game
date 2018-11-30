package fall2018.csc2017.gamecentre.database;

import android.util.Log;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;


/**
 * The Database for the entire app.
 */
public class Database {
    /**
     * The Firestore Database.
     */
    private static Firestore db = FirestoreClient.getFirestore();

    /**
     * Gets the database.
     *
     * @return the Firestore database
     */
    public static Firestore getDatabase() {
        Log.d("Database:", "Database accessed");
        return db;
    }
}
