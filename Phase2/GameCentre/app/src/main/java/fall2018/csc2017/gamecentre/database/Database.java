package fall2018.csc2017.gamecentre.database;

import android.util.Log;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;

public class Database {

    private static Firestore db;

    private static void initDatabase() {
        // Code adapted from https://firebase.google.com/docs/admin/setup#initialize_the_sdk
        try {
            String path = Database.class
                    .getResource("Database.class").toString();

            FileInputStream serviceAccount = new FileInputStream(path);

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://csc207-phase2.firebaseio.com/")
                    .build();

            FirebaseApp.initializeApp(options);

            db = FirestoreClient.getFirestore();

        } catch(Exception e) {
            Log.e("TEMP", e.toString());
        }
    }

    /**
     * Gets the database.
     *
     * @return the Firestore database
     */
    public static Firestore getDatabase() {
        // If the db hasn't been initialized, do so.
        if (db == null) {
            initDatabase();
        }
        return db;
    }
}
