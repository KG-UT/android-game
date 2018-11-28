package fall2018.csc2017.gamecentre.games.matchingCards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import fall2018.csc2017.gamecentre.game.BoardManager;

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
    MatchingCardsBoardManager() {
        this(4, 4);
    }

    /**
     * Manage a board that has been pre-populated.
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
        for (int tileNum = 0; tileNum != numTiles/2; tileNum++) {
            tiles.add(new MatchingCardsTile(tileNum));
            tiles.add(new MatchingCardsTile(tileNum));
        }

        Collections.shuffle(tiles);
        setBoard(new MatchingCardsBoard(numRows, numCols, tiles));
    }

    @Override
    public boolean puzzleSolved() {
        boolean solved = true;
        MatchingCardsBoard board = (MatchingCardsBoard) getBoard();
        Iterator<Object> boardIterator = board.iterator();
        for(int i = 0; i < MatchingCardsBoard.numTiles(); i++) {
            MatchingCardsTile currentTile = (MatchingCardsTile) boardIterator.next();
            if(!currentTile.isFaceUp()) {
                solved = false;
            }
        }
        return solved;
    }
}
