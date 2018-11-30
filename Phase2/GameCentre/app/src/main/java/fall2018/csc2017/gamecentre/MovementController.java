package fall2018.csc2017.gamecentre;

import android.content.Context;
import android.widget.Toast;

import fall2018.csc2017.gamecentre.abstractClasses.BoardManager;

/**
 * A MovementController that processes different moves.
 */
public class MovementController {

    /**
     * The current board manager.
     */
    private BoardManager boardManager = null;

    /**
     * Creates a new MovementController (no parameters).
     */
    public MovementController() {
    }

    /**
     * Sets the board manager of the MovementController.
     *
     * @param boardManager the BoardManager that is to be set
     */
    public void setBoardManager(BoardManager boardManager) {
        this.boardManager = boardManager;
    }

    /**
     * Processes any taps.
     *
     * @param context the current context
     * @param position the position of the tap
     * @param display a true or false value
     */
    public void processTapMovement(Context context, int position, boolean display) {
        if (boardManager.isValidTap(position)) {
            boardManager.touchMove(position);
            if (boardManager.puzzleSolved()) {
                Toast.makeText(context, "YOU WIN!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "Invalid Tap", Toast.LENGTH_SHORT).show();
        }
    }
}
