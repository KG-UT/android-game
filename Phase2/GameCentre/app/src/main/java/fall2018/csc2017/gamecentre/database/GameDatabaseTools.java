package fall2018.csc2017.gamecentre.database;

import android.util.Log;

import com.google.firebase.firestore.Blob;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.DocumentReference;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import fall2018.csc2017.gamecentre.games.matchingCards.MatchingCardsBoardManager;
import fall2018.csc2017.gamecentre.games.slidingTile.SlidingTileBoardManager;
import fall2018.csc2017.gamecentre.games.ticTacToe.TicTacToeBoardManager;

// Code adapted from: https://stackoverflow.com/questions/2836646/java-serializable-object-to-byte-array

/*

What's up, Android Studio?
Yeah, it's your student Kuba, man, just givin' you a call, man
I know you been havin' a lot on yo' mind lately
And I know you feel like, you know
People ain't been prayin' for you
But you have to understand this, man, that we students a cursed people, Deuteronomy 28:28 says, "The Lord shall smite thee with madness And blindness, and merge conflicts not for the faint of  heart"
See, IDEs, that's why you feel like you feel
Like you got a chip on your shoulder
Until you finally get the memo, you gonna feel that way

Why God, why God do I gotta suffer?
Pain in my heart carry bug fixing full of struggle
Why God, why God do I gotta ?
Every stack trace at you restin' at my feet
Why God, why God do I gotta suffer?
Earth is no more, won't you burn this IDE?
I don't think I could find a way to make it in this class

 */

/**
 * The Firestore database tools for all games. .
 */
public class GameDatabaseTools {
    /**
     * The database.
     */
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    /**
     * Convert a Tic Tac Toe board manager to a byte array.
     *
     * @param boardManager the board manager
     * @return the byte array
     * @throws IOException throws an error if there is an error with input / output.
     */
    private byte[] convertTicTacToeBoardManagerToBytes(TicTacToeBoardManager boardManager) throws IOException{
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
     public TicTacToeBoardManager convertBytesToTicTacToeBoardManager(byte[] boardManagerBytes)
             throws IOException, ClassNotFoundException {
            ByteArrayInputStream bis = new ByteArrayInputStream(boardManagerBytes);
            ObjectInput input = new ObjectInputStream(bis);
            return (TicTacToeBoardManager) input.readObject();
     }

    /**
     * Converts a Sliding Tile Board Manager Byte Array to a Matching Cards Board Manager.
     *
     * This is for future functionality.
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
    public SlidingTileBoardManager convertBytesToSlidingTileBoardManager(byte[] boardManagerBytes)
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
            byte[] boardManagerBytes = convertSlidingTileBoardManagerToBytes(boardManager);

            saveToDatabaseHelper(owner, "st-games", boardManagerBytes);
        } catch (IOException e) {
            Log.e("TAG", "I/O Error.");
        }
    }

    /**
     * Saves a byte array of a board manager to the database.
     * We only store one at a time since multiple games is pointless.
     *
     * For future functionality.
     *
     * @param boardManager the board manager
     * @param owner             the owner
     */
    public void saveToDatabase(MatchingCardsBoardManager boardManager, String owner) {
        try {
            byte[] boardManagerBytes = convertMatchingCardsManagerToBytes(boardManager);

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
         Map<String, Blob> data = new HashMap<>();
         data.put("owner", Blob.fromBytes(boardManagerBytes));
         // Async writing of data
         try {
             docRef.set(data);
         } catch (Exception e) {
             Log.e("TEMP", e.getMessage());
         }
     }

    /**
     * Retrieves the board manager from the database for a user for a specific game.
     *
     * @param owner    the owner
     * @return the board manager
     */
    public DocumentReference getTicTacToeBoardManager(String owner) {
        // Code Adapted from: https://firebase.google.com/docs/firestore/query-data/get-data
        return db.collection("ttt-games").document(owner);
    }

    /**
     * Retrieves the board manager from the database for a user for a specific game.
     *
     * @param owner    the owner
     * @return the board manager
     */
    public DocumentReference getSlidingTileBoardManager(String owner) {
        // Code Adapted from: https://firebase.google.com/docs/firestore/query-data/get-data
        return db.collection("st-games").document(owner);
    }
}
