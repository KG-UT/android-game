package fall2018.csc2017.gamecentre.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import java.util.List;

import fall2018.csc2017.gamecentre.database.dao.GoBoardManagerDao;
import fall2018.csc2017.gamecentre.database.dao.STBoardManagerDao;
import fall2018.csc2017.gamecentre.database.dao.TTTBoardManagerDao;
import fall2018.csc2017.gamecentre.database.dao.UserDao;
import fall2018.csc2017.gamecentre.database.entity.GoBoardManager;
import fall2018.csc2017.gamecentre.database.entity.SlidingTileBoardManager;
import fall2018.csc2017.gamecentre.database.entity.User;

/**
 * The Database for the app.
 */
@Database(entities = {User.class,
                      GoBoardManager.class,
                      TicTacToeBoardManager.class,
                      SlidingTileBoardManager.class},
                      version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    /**
     * Used to access the UserDao queries.
     *
     * @return the User Data Access Object
     */
    public abstract UserDao userDao();

    /**
     * Used to access the STBoardManagerDao queries.
     *
     * @return the Sliding Tile Board Manager Data Access Object
     */
    public abstract STBoardManagerDao stBoardManagerDao();

    /**
     * Used to access the TTTBoardManagerDao queries.
     *
     * @return the Tic Tac Toe Board Manager Data Access Object
     */
    public abstract TTTBoardManagerDao tttBoardManagerDao();

    /**
     * Used to access the GoBoardManagerDao queries.
     *
     * @return the Go Board Manager Data Access Object
     */
    public abstract GoBoardManagerDao goBoardManagerDao();

    /**
     * Gets app database.
     *
     * @param context the context
     * @return the application database
     */
    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "GameCentre").build();
        }
        return INSTANCE;
    }

    /**
     * Destroys the instance of the database.
     */
    private static void destroyInstance() {
        INSTANCE = null;
    }

    /**
     * Adds a user to the database.
     *
     * @param db    the database instance.
     * @param user  The user to be inserted
     * @return      The user that was just inserted.
     */
    private static User insertUser(final AppDatabase db, User user) {
        db.userDao().insert(user);
        return user;
    }

    /**
     * Retrieves a specific user.
     *
     * @param db    The database instance.
     * @param username  The username for the user we want to return.
     * @return      The specified user.
     */
    private static User getUser(final AppDatabase db, String username) {
        return db.userDao().findByName(username);
    }

    /** TODO: Force view back to login. OR reevaluate need for this.
     * Deletes a user from the database.
     *
     * @param db    The database instance.
     * @param user  The user to be deleted.
     * @return      The user that was deleted.
     */
    private static User deleteUser(final AppDatabase db, User user) {
        db.userDao().delete(user);
        return user;
    }

    /**
     * Updates a user's password in the database.
     *
     * @param db    The database instance
     * @param user  The user whose password will be updated in the database.
     * @return      The user whose password we updated.
     */
    private static User updatePassword(final AppDatabase db, User user) {
        return db.userDao().updatePassword(user.getUid(), user.getPassword());
    }

    /**
     * Inserts a Sliding Tile Board Manager into the database.
     *
     * @param db    The database instance.
     * @param STBM  The ST Board Manager to be inserted.
     * @return      The inserted ST Board Manager.
     */
    private static SlidingTileBoardManager addSTBoardManager(final AppDatabase db,
                                                             SlidingTileBoardManager STBM) {
        db.stBoardManagerDao().insert(STBM);
        return STBM;
    }

    /**
     * Returns all the sliding tile games associated with a given user.
     *
     * @param db    The database instance
     * @param owner The user's username
     * @return      A list of all Sliding Tile Board Managers associated with a user.
     */
    private static List<SlidingTileBoardManager> getUsersSlidingTIleGames(final AppDatabase db,
                                                                          String owner) {
        return db.stBoardManagerDao().getUserSavedGames(owner);
    }

    /**
     * Inserts a Tic Tac Toe Board Manager into the database.
     *
     * @param db    The database instance.
     * @param TTTBM  The TTT Board Manager to be inserted.
     * @return      The inserted TTT Board Manager.
     */
    private static TicTacToeBoardManager addTTTBoardManager(final AppDatabase db,
                                                             TicTacToeBoardManager TTTBM) {
        db.tttBoardManagerDao().insert(TTTBM);
        return TTTBM;
    }

    /**
     * Returns all the sliding tile games associated with a given user.
     *
     * @param db    The database instance
     * @param owner The user's username
     * @return      A list of all Sliding Tile Board Managers associated with a user.
     */
    private static List<TicTacToeBoardManager> getUsersTicTacToeGames(final AppDatabase db,
                                                                          String owner) {
        return db.tttBoardManagerDao().getUserSavedGames(owner);
    }







}
