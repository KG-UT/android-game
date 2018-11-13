package fall2018.csc2017.gamecentre;

import java.util.HashMap;
import java.util.ArrayList;

/**
 * This is the object representation of a user stored in the database.
 */
public class User {
    /**
     *  The User's userId. Type must be <tt>long</tt> for use in SQLite.
     */
    private long userId;

    /**
     * The username (email) for a given user.
     */
    private String username;

    /**
     * The user's password.
     */
    private String password;

    /**
     * Instantiates a new User.
     *
     * @param userId   the user id  (primary key in the login table)
     * @param username the username (email)
     * @param password the password
     */
    public User(long userId, String username, String password){
        this.userId=userId;
        this.username=username;
        this.password=password;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public long getUserId() {
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the user's password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets a new password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
