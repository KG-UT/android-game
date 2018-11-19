package fall2018.csc2017.gamecentre.Database.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import fall2018.csc2017.gamecentre.Database.Entity.TicTacToeBoardManager;

/**
 * The Tic Tac Toe Board Manager Data Access Object
 */
@Dao
public interface TTTBoardManagerDao {

    /**
     * Returns a list of all the user's saved Tic Tac Toe games.
     *
     * @param owner The user's username.
     * @return  A list of all the Tic Tac Toe Board Managers associated with this user.
     */
    @Query("SELECT * FROM TTTBoards WHERE owner=:owner")
    List<TicTacToeBoardManager> getUserSavedGames(String owner);

    /**
     * Inserts a Tic Tac Toe Board Manager into the database.
     *
     * @param TTTBoardManager   The TTT Board Manager to be inserted.
     */
    @Insert
    void insert(TicTacToeBoardManager TTTBoardManager);
}
