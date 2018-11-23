package fall2018.csc2017.gamecentre.database.entity;



/**
 * This is the object representation of a user stored in the database.
 *
 * Code adapted from: https://medium.com/@ajaysaini.official/building-database-with-room-persistence-library-ecf7d0b8f3e9
 */
public class User {
    /**
     *  The User's uid.
     */
    private int uid;

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
     * @param uid      the user id  (primary key in the LoginInfo table)
     * @param username the username (email)
     * @param password the password
     */
    public User(int uid, String username, String password){
        this.uid=uid;
        this.username=username;
        this.password=password;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public int getUid() {
        return uid;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUid(int userId) {
        this.uid = userId;
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
