package fall2018.csc2017.gamecentre;

/**
 * The object representation of a sliding tile saved game in the database.
 */
public class SlidingTileSavedGame {

    /**
     * The id of the saved game in the database.
     */
    private long _id;

    /**
     * The file path for the game
     */
    private String slidingTileSavedGamePath;

    /**
     * The owner of the game (user)
     */
    private String owner;

    /**
     * Instantiates a new Sliding tile saved game.
     *
     * @param _id                      the id
     * @param slidingTileSavedGamePath the sliding tile saved game path
     * @param owner                    the owner (user)
     */
    public SlidingTileSavedGame(long _id, String slidingTileSavedGamePath, String owner) {
        this._id = _id;
        this.owner = owner;
        this.slidingTileSavedGamePath = slidingTileSavedGamePath;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public long get_id() { return _id; }

    /**
     * Sets id.
     *
     * @param _id the id
     */
    public void set_id(long _id) {
        this._id = _id;
    }

    /**
     * Gets owner.
     *
     * @return the owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Sets owner.
     *
     * @param owner the owner
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * Gets sliding tile saved game path.
     *
     * @return the sliding tile saved game path
     */
    public String getSlidingTileSavedGamePath() {
        return slidingTileSavedGamePath;
    }

    /**
     * Sets sliding tile saved game path.
     *
     * @param slidingTileSavedGamePath the sliding tile saved game path
     */
    public void setSlidingTileSavedGamePath(String slidingTileSavedGamePath) {
        this.slidingTileSavedGamePath = slidingTileSavedGamePath;
    }
}



