package fall2018.csc2017.gamecentre.Database.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import fall2018.csc2017.gamecentre.Database.Entity.GoBoardManager;

/**
 * The Sliding Tile Board Manager Data Access Object
 */
@Dao
public interface GoBoardManagerDao {

    /**
     * Returns a list of all the user's saved Sliding Tile games.
     *
     * @param owner The user's username.
     * @return  A list of all the Sliding Tile Board Manager associated with this user.
     */
    @Query("SELECT * FROM GoBoards WHERE owner = :owner")
    List<GoBoardManager> getUserSavedGames(String owner);

    /**
     * Inserts a Sliding Tile Board Manager into the database.
     *
     * @param goBoardManager The Go Board Manager to be inserted.
     */
    @Insert
    void insert(GoBoardManager goBoardManager);

}