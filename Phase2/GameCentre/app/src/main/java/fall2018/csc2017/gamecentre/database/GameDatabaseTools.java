package fall2018.csc2017.gamecentre.database;

import android.util.Log;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import fall2018.csc2017.gamecentre.abstractClasses.BoardManager;


// Code adapted from: https://stackoverflow.com/questions/2836646/java-serializable-object-to-byte-array

/**
 * The Firestore database tools for
 */
public class GameDatabaseTools {
    /**
     * The database.
     */
    private Firestore db = Database.getDatabase();

    /**
     * Convert a board manager to a byte array.
     *
     * @param boardManager the board manager
     * @return the byte array
     * @throws IOException throws an error if there is an error with input / output.
     */
    private byte[] convertBoardManagerToBytes(BoardManager boardManager) throws IOException{
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutput output = new ObjectOutputStream(bos)) {
             output.writeObject(boardManager);
            return bos.toByteArray();
        }
    }

    /**
     * Converts a Board Manager Byte Array to a Board Manager.
     *
     * @param boardManagerBytes       The Board Manager byte array.
     * @return                        Returns the Board Manager object from a byte array.
     * @throws IOException            Throws an exception if there is an error with input/output.
     * @throws ClassNotFoundException Throws an exception if the board manager class is not found.
     */
     private BoardManager convertBytesToBoardManager(byte[] boardManagerBytes)
             throws IOException, ClassNotFoundException {
            try (ByteArrayInputStream bis = new ByteArrayInputStream(boardManagerBytes);
                 ObjectInput input = new ObjectInputStream(bis)) {
                return (BoardManager) input.readObject();
            }
     }

    /**
     * Saves a byte array of a board manager to the database.
     * We only store one at a time since multiple games is pointless.
     *
     * @param boardManager the board manager
     * @param gameType          the game type
     * @param owner             the owner
     */
    public void saveToDatabase(BoardManager boardManager, String gameType, String owner) {
        // TODO: Is this an issue?
        byte[] boardManagerBytes = convertBoardManagerToBytes(boardManager);
         DocumentReference docRef = db.collection(gameType).document(owner);
         // Adds document data with id of "owner" and the score.
         Map<String, Object> data = new HashMap<>();
         data.put("owner", boardManagerBytes);
         // Async writing of data
         try {
             docRef.set(data);
         } catch (Exception e) {
             Log.e("TEMP", "Error inserting board manager bytes into database.");
         }
     }

    /**
     * Retrieves the board manager from the database for a user for a specific game.
     *
     * @param owner    the owner
     * @param gameType the game type
     * @return the board manager
     */
    public BoardManager getBoardManager(String owner, String gameType){
        // Code Adapted from: https://firebase.google.com/docs/firestore/query-data/get-data
        DocumentReference docRef = db.collection(gameType).document(owner);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();


        BoardManager boardManager;
        if (document.exists()) {
            // TODO: if this an issue?
            byte[] boardManagerBytes = (byte[]) document.getData().get(owner);
            // TODO: Is this one an issue?
            BoardManager boardManager = convertBytesToBoardManager(boardManagerBytes);
        }
        return boardManager;
     }
}
