package fall2018.csc2017.gamecentre.games.Go;

import android.support.annotation.NonNull;

import java.io.Serializable;

public class GoTile implements Comparable<GoTile>, Serializable {

    /**
     * The background id to find the go tile image.
     */
    private int background;

    /**
     * The unique id.
     */
    private int id;

    /**
     * Empty tile id
     */
    public static final int EMPTY_ID = 9999;

    /**
     * The white stone's id.
     */
    public static final int whiteStone = 0;

    /**
     * The black stone's id.
     */
    public static final int blackStone = 1;

    public int getBackground() { return background; }

    /**
     * Return the tile id.
     *
     * @return the tild id.
     */
    public int getId() { return id; }

    public String getDisplayNumber() {
        if (this.id == EMPTY_ID) {
            return "";
        } else {
            return String.valueOf(this.id);
        }
    }

    /** TODO: fuck
     * Instantiates a new Go tile.
     *
     * @param id         the id
     * @param background the background
     */
    public GoTile(int id, int background) {
        this.id = id;
        this.background = background;
    }

    @Override
    public int compareTo(@NonNull GoTile o) { return o.id - this.id; }
}
