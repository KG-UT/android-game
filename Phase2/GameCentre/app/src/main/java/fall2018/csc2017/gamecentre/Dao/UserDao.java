package fall2018.csc2017.gamecentre.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import fall2018.csc2017.gamecentre.Entity.User;

/**
 * The User Data Access Object
 *
 * Code adapted from: https://medium.com/@ajaysaini.official/building-database-with-room-persistence-library-ecf7d0b8f3e9
 */
@Dao
public interface UserDao {
    /**
     * Returns all users.
     *
     * @return a List of all Users in the database.
     */
    @Query("SELECT * FROM user")
    List<User> getAll();

    /**
     * Returns a specific User.
     *
     * @param username  The username of who we want to look up.
     * @return  The User object we looked up.
     */
    @Query("SELECT * FROM user WHERE username = 'username'")
    User findByName(String username);

    /**
     * Inserts any amount of Users into the database.
     *
     * @param user  The User to be inserted.
     */
    @Insert
    void insert(User user);

    /**
     * Deletes a User from the database.
     *
     * @param user  The User to be deleted
     */
    @Delete
    void delete(User user);

    @Query("UPDATE ")
    void updateUsername(User user);
}
