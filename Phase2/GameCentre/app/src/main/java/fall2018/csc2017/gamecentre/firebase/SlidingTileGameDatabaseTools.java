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
import fall2018.csc2017.gamecentre.games.slidingTile.SlidingTileBoardManager;

import static fall2018.csc2017.gamecentre.view.LoginActivity.currentUser;

// TODO: JavaDocs
public final class SlidingTileGameDatabaseTools extends GameDatabaseTools {


    @Override
    String serializeBoardManager(BoardManager boardManager) {
        return null;
    }

    @Override
    BoardManager deSerializeBoardManager(String serializedBoardManager) {
        return null;
    }

    @Override
    BoardManager retrieveBoardManager(String gameKeyValue) {
        return null;
    }

    @Override
    void saveToDatabase(BoardManager boardManager) {

    }
}
