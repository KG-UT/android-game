package fall2018.csc2017.gamecentre.database;

import android.util.Log;

import com.google.firebase.firestore.FirebaseFirestore;


/**
 * The Database for the entire app.
 */
public class Database {
    /**
     * The Firestore Database.
     */
    private static FirebaseFirestore db = FirebaseFirestore.getInstance();

    /**
     * Gets the database.
     *
     * @return the Firestore database
     */
    public static FirebaseFirestore getDatabase() {
        Log.d("Database:", "Database accessed");
        return db;
    }
}
