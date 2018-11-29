package fall2018.csc2017.gamecentre.firebase;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import fall2018.csc2017.gamecentre.abstractClasses.BoardManager;
import fall2018.csc2017.gamecentre.boardManagers.SlidingTileBoardManager;

import static fall2018.csc2017.gamecentre.view.LoginActivity.currentUser;

// TODO: JavaDocs
public final class SlidingTileDatabaseTools extends DatabaseTools {

    private DatabaseReference mDatabase;

    private SlidingTileBoardManager savedSTBM;


    public SlidingTileDatabaseTools() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        this.mDatabase = database.getReference();
    }

    /** TODO: Make text pop up when shit goes well / poorly.
     * Save the board manager to fileName.
     */
    public void saveToDatabase(BoardManager boardManager) {
        SlidingTileBoardManager slidingTileBoardManager = (SlidingTileBoardManager) boardManager;
        String ownerKeyValue = slidingTileBoardManager.getOwnerKeyValue();
        String gameKeyValue;

        if (slidingTileBoardManager.getGameKeyValue() == null) {
            gameKeyValue = mDatabase.child("users").child(ownerKeyValue)
                    .child("st-games").push().getKey();
        } else {
            gameKeyValue = slidingTileBoardManager.getGameKeyValue();
        }

        String serializedSTBoardManager = serializeBoardManager(slidingTileBoardManager);

        mDatabase.child("users").child(ownerKeyValue).child("st-games")
                // TODO: Is there ever actualy null?
                .child(gameKeyValue).setValue(serializedSTBoardManager)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("TEMP", "Successfully added to the database.");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TEMP", "There was an error adding to the database.");
                    }
                });
    }

    // Code adapted from https://stackoverflow.com/questions/43241450/retrieving-specific-data-from-firebase-database
    @Override
    public BoardManager retrieveBoardManager(String gameKeyValue) {
        String ownerKeyValue = currentUser.getUid();
        DatabaseReference ref = mDatabase.child("users").child(ownerKeyValue)
                .child("st-games").child(gameKeyValue);

        // TODO: This is pretty bad, but it works.
        final ArrayList<SlidingTileBoardManager> savedSTBMList = new ArrayList<>();
        ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                String serializedSTBM = dataSnapshot.getValue(String.class);
                savedSTBMList.add((SlidingTileBoardManager) deSerializeBoardManager(serializedSTBM));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("TEMP", "Error in getting the serialized board manager.");
            }
        });
        // TODO: less cancer
        savedSTBM = savedSTBMList.get(0);
        savedSTBMList.remove(0);

        return savedSTBM;
    }

    // Code adapted from https://stackoverflow.com/questions/8887197/reliably-convert-any-object-to-string-and-then-back-again/8887244
    // TODO: ADD LOGGING
    @Override
    public String serializeBoardManager(BoardManager boardManager) {
        SlidingTileBoardManager slidingTileBoardManager = (SlidingTileBoardManager) boardManager;

        String serializedSTBoardManager = "";

        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream so = new ObjectOutputStream(bo);
            so.writeObject(slidingTileBoardManager);
            so.flush();
            serializedSTBoardManager = bo.toString();
        } catch (Exception e) {
            System.out.println(e);
        }
        return serializedSTBoardManager;
    }

    // TODO: Add Logging.
    @Override
    public BoardManager deSerializeBoardManager(String serializedSTBoardManager) {
        SlidingTileBoardManager stBoardManager = new SlidingTileBoardManager();
        try {
            byte b[] = serializedSTBoardManager.getBytes();
            ByteArrayInputStream bi = new ByteArrayInputStream(b);
            ObjectInputStream si = new ObjectInputStream(bi);
            stBoardManager = (SlidingTileBoardManager) si.readObject();
        } catch (Exception e) {
            System.out.println(e);
        }
        return stBoardManager;
    }

    public SlidingTileBoardManager getSavedSTBM() {
        return savedSTBM;
    }

    public void setSavedSTBM(SlidingTileBoardManager savedSTBM) {
        this.savedSTBM = savedSTBM;
    }
}
