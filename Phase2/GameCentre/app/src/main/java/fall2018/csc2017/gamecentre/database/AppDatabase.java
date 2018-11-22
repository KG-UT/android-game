package fall2018.csc2017.gamecentre.database;

import android.app.Application;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;

import java.io.File;
import java.util.List;

import fall2018.csc2017.gamecentre.database.dao.GoBoardManagerDao;
import fall2018.csc2017.gamecentre.database.dao.LoginDao;
import fall2018.csc2017.gamecentre.database.dao.STBoardManagerDao;
import fall2018.csc2017.gamecentre.database.dao.TTTBoardManagerDao;
import fall2018.csc2017.gamecentre.database.entity.GoBoardManager;
import fall2018.csc2017.gamecentre.database.entity.SlidingTileBoardManager;
import fall2018.csc2017.gamecentre.database.entity.TicTacToeBoardManager;
import fall2018.csc2017.gamecentre.database.entity.UserTable;

// Code adapted from: https://developer.android.com/training/data-storage/room/migrating-db-versions
/** TODO: check Room cannot create SQLIte connection to verify queries in depth.
 * The Database for the app.
 */
@Database(entities = {UserTable.class,
                      GoBoardManager.class,
                      TicTacToeBoardManager.class,
                      SlidingTileBoardManager.class},
                      version = 2,
                      exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    /**
     * Used to access the UserDao queries.
     *
     * @return the User Data Access Object
     */
    public abstract LoginDao loginDao();

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
    static AppDatabase getAppDatabase(Context context) {

        if (INSTANCE == null) {
            // Synchronize the database.
            synchronized (AppDatabase.class) {
                // If after being synchronized, the instance is null, then create the db.
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "GameCentre")
                            .addMigrations(MIGRATION_1_2).build();
                }
            }
        }
        return INSTANCE;
    }

    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("DROP TABLE `user`");
            database.execSQL("CREATE TABLE IF NOT EXISTS" +
                    " `LoginDetails` (`Id` INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "`Email` TEXT, `Password` TEXT)");
        }
    };










}
