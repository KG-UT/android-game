package fall2018.csc2017.gamecentre.firebase;

import android.util.Log;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;

import fall2018.csc2017.gamecentre.view.BaseLoginActivity;

public class InitializeDatabase {

    private static Firestore db;

    public void initDatabase() {
        // Code adapted from https://firebase.google.com/docs/admin/setup#initialize_the_sdk
        try {
            String path = InitializeDatabase.class
                    .getResource("InitializeDatabase.class").toString();

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

    public static Firestore getDatabase() {
        return db;
    }
}
