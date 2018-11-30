package fall2018.csc2017.gamecentre;



/**
 * This is the object representation of a user stored in the database.
 */
public class User {
    /**
     *  The User's uid.
     */
    private String uid;

    /**
     * The username (email) for a given user.
     */
    private String username;


    /**
     * No-arg constructor for Firebase.
     */
    public User() {}

    /**
     * Instantiates a new User.
     *
     * @param uid      the user id  (primary key in the LoginInfo table)
     * @param username the username
     */
    public User(String uid, String username){
        this.uid=uid;
        this.username=username;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public String getUid() {
        return uid;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUid(String userId) {
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
}
