package fall2018.csc2017.gamecentre.games.matchingCards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import fall2018.csc2017.gamecentre.abstractClasses.BoardManager;

/**
 * Manage the Board, flipping cards up/down, checking for a win
 */
public class MatchingCardsBoardManager extends BoardManager {

    /*
     * The game score.
     */
    private int score = 0;

    /**
     * Manage a new 4 by 4 shuffled board
     */
   public MatchingCardsBoardManager() {
        this(4, 4);
    }

    /**
     * Manage a board that has been pre-populated.
     *
     * @param board the board
     */
    public MatchingCardsBoardManager(MatchingCardsBoard board) {
        super(board);
    }

    /**
     * Manage a new shuffled board.
     * PRECONDITION: numRows*numCols must be an even number
     */
    public MatchingCardsBoardManager(int numRows, int numCols) {
        List<MatchingCardsTile> tiles = new ArrayList<>();
        final int numTiles = numRows * numCols;
        for (int tileNum = 0; tileNum != numTiles / 2; tileNum++) {
            tiles.add(new MatchingCardsTile(tileNum));
            tiles.add(new MatchingCardsTile(tileNum));
        }

        Collections.shuffle(tiles);
        setBoard(new MatchingCardsBoard(numRows, numCols, tiles));
    }

    /**
     * Return whether the tiles are all face-up.
     *
     * @return whether the tiles are all face-up.
     */
    @Override
    public boolean puzzleSolved() {
        boolean solved = true;
        MatchingCardsBoard board = getBoard();
        Iterator<Object> boardIterator = board.iterator();
        for (int i = 0; i < MatchingCardsBoard.numTiles(); i++) {
            MatchingCardsTile currentTile = (MatchingCardsTile) boardIterator.next();
            if (!currentTile.isFaceUp()) {
                solved = false;
            }
        }
        return solved;
    }

    /**
     * Process a touch at position in the board, flipping cards when appropriate.
     *
     * @param position the position
     */
    @Override
    public void touchMove(int position) {
        int row = position / MatchingCardsBoard.getNumRows();
        int col = position % MatchingCardsBoard.getNumCols();
        MatchingCardsBoard board = getBoard();

        // In case you try to do a move, while two temp cards are already up
        if (!board.twoTempCardsAreUp()){
            board.flipCardUp(row, col);
            score += 1;
        } else{
            ArrayList<MatchingCardsTile> faceupTiles = board.getTempFaceupCards();
            if (!faceupTiles.get(0).isEqual(faceupTiles.get(1))) {
                board.flipTempCardsDown();
            }
            board.clearTempFaceUpCards();
        }
    }

    /**
     * Return the current board.
     */
    public MatchingCardsBoard getBoard() { return (MatchingCardsBoard) super.getBoard(); }

    /**
     * returns the current score
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * states if the tap is valid
     * @param position the position of the tap
     * @return whether the tap is valid
     */
    @Override
    public boolean isValidTap(int position) {
        int row = position / MatchingCardsBoard.getNumCols();
        int col = position % MatchingCardsBoard.getNumRows();
        MatchingCardsTile tile = getBoard().getCard(row, col);
        return !tile.isFaceUp();
    }
}
