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
import fall2018.csc2017.gamecentre.games.matchingCards.MatchingCardsBoardManager;
import fall2018.csc2017.gamecentre.games.slidingTile.SlidingTileBoardManager;
import fall2018.csc2017.gamecentre.games.ticTacToe.TicTacToeBoardManager;


// Code adapted from: https://stackoverflow.com/questions/2836646/java-serializable-object-to-byte-array

/**
 * The Firestore database tools for all games.
 */
public class GameDatabaseTools {
    /**
     * The database.
     */
    private Firestore db = Database.getDatabase();

    /**
     * Convert a Tic Tac Toe board manager to a byte array.
     *
     * @param boardManager the board manager
     * @return the byte array
     * @throws IOException throws an error if there is an error with input / output.
     */
    private byte[] convertTicTacToeBoardManagerToBytes(BoardManager boardManager) throws IOException{
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutput output = new ObjectOutputStream(bos)) {
             output.writeObject(boardManager);
            return bos.toByteArray();
        }
    }

    /**
     * Convert a Slidng tile board manager to a byte array.
     *
     * @param boardManager the board manager
     * @return the byte array
     * @throws IOException throws an error if there is an error with input / output.
     */
    private byte[] convertSlidingTileBoardManagerToBytes(SlidingTileBoardManager boardManager) throws IOException{
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutput output = new ObjectOutputStream(bos)) {
            output.writeObject(boardManager);
            return bos.toByteArray();
        }
    }

    /**
     * Convert a Slidng tile board manager to a byte array.
     *
     * @param boardManager the board manager
     * @return the byte array
     * @throws IOException throws an error if there is an error with input / output.
     */
    private byte[] convertMatchingCardsManagerToBytes(MatchingCardsBoardManager boardManager) throws IOException{
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutput output = new ObjectOutputStream(bos)) {
            output.writeObject(boardManager);
            return bos.toByteArray();
        }
    }

    /**
     * Converts a Tic Tac Toe Board Manager Byte Array to a Board Manager.
     *
     * @param boardManagerBytes       The Board Manager byte array.
     * @return                        Returns the Board Manager object from a byte array.
     * @throws IOException            Throws an exception if there is an error with input/output.
     * @throws ClassNotFoundException Throws an exception if the board manager class is not found.
     */
     private TicTacToeBoardManager convertBytesToTicTacToeBoardManager(byte[] boardManagerBytes)
             throws IOException, ClassNotFoundException {
            try (ByteArrayInputStream bis = new ByteArrayInputStream(boardManagerBytes);
                 ObjectInput input = new ObjectInputStream(bis)) {
                return (TicTacToeBoardManager) input.readObject();
            }
     }

    /**
     * Converts a Sliding Tile Board Manager Byte Array to a Matching Cards Board Manager.
     *
     * @param boardManagerBytes       The Board Manager byte array.
     * @return                        Returns the Board Manager object from a byte array.
     * @throws IOException            Throws an exception if there is an error with input/output.
     * @throws ClassNotFoundException Throws an exception if the board manager class is not found.
     */
    private MatchingCardsBoardManager convertBytesToMatchingCardsBoardManager(byte[] boardManagerBytes)
            throws IOException, ClassNotFoundException {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(boardManagerBytes);
             ObjectInput input = new ObjectInputStream(bis)) {
            return (MatchingCardsBoardManager) input.readObject();
        }
    }

    /**
     * Converts a Sliding Tile Board Manager Byte Array to a Sliding Tile Board Manager.
     *
     * @param boardManagerBytes       The Board Manager byte array.
     * @return                        Returns the Board Manager object from a byte array.
     * @throws IOException            Throws an exception if there is an error with input/output.
     * @throws ClassNotFoundException Throws an exception if the board manager class is not found.
     */
    private SlidingTileBoardManager convertBytesToSlidingTileBoardManager(byte[] boardManagerBytes)
            throws IOException, ClassNotFoundException {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(boardManagerBytes);
             ObjectInput input = new ObjectInputStream(bis)) {
            return (SlidingTileBoardManager) input.readObject();
        }
    }

    /**
     * Saves a byte array of a board manager to the database.
     * We only store one at a time since multiple games is pointless.
     *
     * @param boardManager the board manager
     * @param owner             the owner
     */
    public void saveToDatabase(TicTacToeBoardManager boardManager, String owner) {
        try {
            byte[] boardManagerBytes = convertTicTacToeBoardManagerToBytes(boardManager);

            saveToDatabaseHelper(owner, "ttt-games", boardManagerBytes);
        } catch (IOException e) {
            Log.e("TAG", "I/O Error.");
        }
     }

    /**
     * Saves a byte array of a board manager to the database.
     * We only store one at a time since multiple games is pointless.
     *
     * @param boardManager the board manager
     * @param owner             the owner
     */
    public void saveToDatabase(SlidingTileBoardManager boardManager, String owner) {
        try {
            byte[] boardManagerBytes = convertTicTacToeBoardManagerToBytes(boardManager);

            saveToDatabaseHelper(owner, "st-games", boardManagerBytes);
        } catch (IOException e) {
            Log.e("TAG", "I/O Error.");
        }
    }

    /**
     * Saves a byte array of a board manager to the database.
     * We only store one at a time since multiple games is pointless.
     *
     * @param boardManager the board manager
     * @param owner             the owner
     */
    public void saveToDatabase(MatchingCardsBoardManager boardManager, String owner) {
        try {
            byte[] boardManagerBytes = convertTicTacToeBoardManagerToBytes(boardManager);

            saveToDatabaseHelper(owner, "mc-games", boardManagerBytes);
        } catch (IOException e) {
            Log.e("TAG", "I/O Error.");
        }
    }

    /**
     * Modularize the actual saving process.
     *
     * @param owner             the owner
     * @param gameType          the game type to be saved to
     * @param boardManagerBytes byte array of a board manager
     */
     private void saveToDatabaseHelper(String owner, String gameType, byte[] boardManagerBytes) {

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
     * @return the board manager
     */
    public TicTacToeBoardManager getTicTacToeBoardManager(String owner) {
        // Code Adapted from: https://firebase.google.com/docs/firestore/query-data/get-data
        DocumentReference docRef = db.collection("ttt-games").document(owner);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        try {
            DocumentSnapshot document = future.get();
            if (document.exists()) {
                // TODO: if this an issue?
                byte[] boardManagerBytes = (byte[]) document.getData().get(owner);

                return convertBytesToTicTacToeBoardManager(boardManagerBytes);
            }
        } catch (Exception e) {
            Log.e("TAG", "Error getting board manager.");
        }
        // TODO: Make this less cancer.
        return null;
    }

    /**
     * Retrieves the board manager from the database for a user for a specific game.
     *
     * @param owner    the owner
     * @return the board manager
     */
    public SlidingTileBoardManager getSlidingTileBoardManager(String owner) {
        // Code Adapted from: https://firebase.google.com/docs/firestore/query-data/get-data
        DocumentReference docRef = db.collection("st-games").document(owner);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        try {
            DocumentSnapshot document = future.get();
            if (document.exists()) {
                // TODO: if this an issue?
                byte[] boardManagerBytes = (byte[]) document.getData().get(owner);

                return convertBytesToSlidingTileBoardManager(boardManagerBytes);
            }
        } catch (Exception e) {
            Log.e("TAG", "Error getting board manager.");
        }
        // TODO: Make this less cancer.
        return null;
    }

    /**
     * Retrieves the board manager from the database for a user for a specific game.
     *
     * @param owner    the owner
     * @return the board manager
     */
    public MatchingCardsBoardManager getMatchingCardsBoardManager(String owner) {
        // Code Adapted from: https://firebase.google.com/docs/firestore/query-data/get-data
        DocumentReference docRef = db.collection("mc-games").document(owner);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        try {
            DocumentSnapshot document = future.get();
            if (document.exists()) {
                // TODO: if this an issue?
                byte[] boardManagerBytes = (byte[]) document.getData().get(owner);

                return convertBytesToMatchingCardsBoardManager(boardManagerBytes);
            }
        } catch (Exception e) {
            Log.e("TAG", "Error getting board manager.");
        }
        // TODO: Make this less cancer.
        return null;
    }
}
