package fall2018.csc2017.gamecentre.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import fall2018.csc2017.gamecentre.Dao.UserDao;
import fall2018.csc2017.gamecentre.Entity.User;

/**
 * The Database for the app.
 */
@Database(entities = {User.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    private static AppDataBase INSTANCE;

    /**
     * Used to access the UserDao queries.
     *
     * @return the User Data Access Object
     */
    public abstract UserDao userDao();

    /**
     * Gets app database.
     *
     * @param context the context
     * @return the app database
     */
    public static AppDataBase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    AppDataBase.class, "GameCentre").build();
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
    private static User addUser(final AppDataBase db, User user) {
        db.userDao().insert(user);
        return user;
    }

    /**
     * Deletes a user from the database.
     *
     * @param db    the database instance.
     * @param user  The user to be deleted.
     * @return      The user that was deleted.
     */
    private static User deleteUser(final AppDataBase db, User user) {
        db.userDao().delete(user);
        return user;
    }


}
